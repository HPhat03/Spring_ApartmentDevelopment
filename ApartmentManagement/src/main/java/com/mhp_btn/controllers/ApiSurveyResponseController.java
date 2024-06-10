package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import com.mhp_btn.services.DetailRequestService;
import com.mhp_btn.services.DetailResponseService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.SurveyRequestService;
import com.mhp_btn.services.SurveyResponseService;
import com.mhp_btn.utils.StringUtil;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiSurveyResponseController {
    @Autowired
    private SurveyResponseService responseService;
    @Autowired
    private RentalConstractService apartmentService;
    @Autowired
    private SurveyRequestService surveyService;
    @Autowired
    private DetailRequestService detailRequestService;
    @Autowired
    private DetailResponseService detailResponseService;
    //Trả về tất cả các phản hồi từ khảo sát về căn hộ.
    @GetMapping(path = "/survey_response/", produces = "application/json")
    public ResponseEntity<List<ApartmentSurveyResponse>> getAllSurveyReponse() {
        List<ApartmentSurveyResponse> surveyResponse = responseService.getAllSurveyResponse();
        return new ResponseEntity<>(surveyResponse, HttpStatus.OK);
    }

    //Trả về thông tin chi tiết của một phản hồi cụ thể dựa trên ID.
    @GetMapping(path = "/survey_response/{id}", produces = "application/json")
    public ResponseEntity<?> getSurveyReponseById(@PathVariable int id) {
        ApartmentSurveyResponse surveyResponse = responseService.getSurveyResponseById(id);
        if (surveyResponse == null) {
            return new ResponseEntity<>("Survey response not found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(surveyResponse, HttpStatus.OK);
    }

// Tạo một phản hồi mới từ khảo sát về căn hộ.
    @PostMapping("/survey_response/")
    public ResponseEntity<?> addSurveyResponse(@RequestBody Map<String, Object> params, Principal p) {
        try {
            // Lấy thông tin từ request body
            String name = (String) params.get("name");
            int apartmentId = (int) params.get("apartmentId");
            int surveyId = (int) params.get("surveyId");
            
            
            ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
            if (apartment == null || apartment.getIsActive() == 0) {
                return new ResponseEntity<>("Căn hộ không khả dụng", HttpStatus.NOT_FOUND);
            }
            if (!apartment.getResidentUser().getUsername().equals(p.getName()))
                return new ResponseEntity<>("Không thể trả lời khảo sát cho chung cư khác", HttpStatus.BAD_REQUEST);
            ApartmentSurveyRequest request = surveyService.getSurveyRequestById(surveyId);
            if (request == null) {
                return new ResponseEntity<>("Không thể tìm thấy khảo sát id: " + surveyId, HttpStatus.NOT_FOUND);
            }
            Date currentDate = new Date();
            if(currentDate.after(request.getEndDate())){
                return new ResponseEntity<>("Khảo sát đã hết hiệu lực!", HttpStatus.BAD_REQUEST);
            }
            if (!name.equals(apartment.getResidentId().getApartmentUser().getName())) {
                boolean valid = false;
                Set<ApartmentOtherMember> others = apartment.getApartmentOtherMemberSet();
                for (ApartmentOtherMember x : others) {
                    System.out.println(x.getName() + " = " + name);
                    if (name.equals(x.getName())) {
                        valid = true;
                    }
                }
                if (!valid) {
                    return new ResponseEntity<>("Bạn không phải thành viên trong căn hộ này", HttpStatus.BAD_REQUEST);
                }
            }
            ApartmentSurveyResponse surveyResponse = new ApartmentSurveyResponse();
            surveyResponse.setSurveyId(request);
            surveyResponse.setName(name);
            surveyResponse.setSubmitDate(currentDate);
            surveyResponse.setApartmentId(apartment);

            // Lưu đối tượng vào cơ sở dữ liệu
            responseService.addSurveyResponse(surveyResponse);
            List<Map<String, Integer>> answers = (List<Map<String, Integer>>) params.get("answers");
            answers.forEach(x -> {
                ApartmentDetailResponse temp = new ApartmentDetailResponse();
                int question = x.get("question_id");
                temp.setQuestionId(detailRequestService.getDetailRequestById(question));
                temp.setResponseId(surveyResponse);
                temp.setAnswer(x.get("answer"));
                detailResponseService.addDetailResponse(temp);
            });
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create survey request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

// Cập nhật thông tin của một phản hồi cụ thể dựa trên ID.
@PatchMapping(value = "/survey_response/{id}", produces = "application/json")
public ResponseEntity<Object> updateSurveyResponseById(@PathVariable int id,
                                                      @RequestBody Map<String, String> updates) {
    ApartmentSurveyResponse surveyResponse = responseService.getSurveyResponseById(id);
    if (surveyResponse == null) {
        return new ResponseEntity<>("Survey response not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
    for (Map.Entry<String, String> entry : updates.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        switch (key) {
            case "apartmentId":
                int apartmentId = Integer.parseInt(value);
                ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
                if (apartment == null) {
                    return new ResponseEntity<>("Apartment rental constract not found with id " + apartmentId, HttpStatus.NOT_FOUND);
                }
                surveyResponse.setApartmentId(apartment);
                break;
            case "surveyId":
                int surveyId = Integer.parseInt(value);
                ApartmentSurveyRequest request = surveyService.getSurveyRequestById(surveyId);
                if (request == null) {
                    return new ResponseEntity<>("Survey request not found with id " + surveyId, HttpStatus.NOT_FOUND);
                }
                surveyResponse.setSurveyId(request);
                break;

            case "submitDate":
                try {
                    Date submitDate = StringUtil.dateFormater().parse(value);
                    surveyResponse.setSubmitDate(submitDate);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Invalid end date format");
                }
                break;
            case "name":
                surveyResponse.setName(value);
                break;
            default:
                throw new IllegalArgumentException("Trường cập nhật không hợp lệ: " + key);
        }

    }
    responseService.updateSurveyResponse(surveyResponse);
    return ResponseEntity.ok(surveyResponse);
}
// Xóa một phản hồi cụ thể dựa trên ID.
    @DeleteMapping(path = "/survey_response/{surveyId}")
    public ResponseEntity<?> deleteSurveyResponseById(@PathVariable("surveyId") int surveyId) {
        ApartmentSurveyResponse response = responseService.getSurveyResponseById(surveyId);
        if (response == null) {
            return new ResponseEntity<>("survey response not found with ID: " + surveyId, HttpStatus.NOT_FOUND);
        }
        responseService.deleteSurveyResponseById(surveyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
