package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.services.DetailRequestService;
import com.mhp_btn.services.SurveyRequestService;
import com.mhp_btn.services.UserService;
import com.mhp_btn.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.PageContext;
import javax.ws.rs.PathParam;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
//@RequestMapping("/api")
public class ApiSurveyRequestController {
    @Autowired
    private SurveyRequestService requestService;
    @Autowired
    private UserService userService;
    @Autowired
    private DetailRequestService detailRequestService;

    //Lay tat ca khao sat
    @GetMapping(path = "/api/survey_request", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<List<ApartmentSurveyRequest>> getAllSurveyRequest(@RequestParam Map<String, String>  params) {
        
        List<ApartmentSurveyRequest> surveyRequests = requestService.getAllSurveyRequest(params);
        return new ResponseEntity<>(surveyRequests, HttpStatus.OK);
    }

    @DeleteMapping(path = "/admin/survey_request/{surveyId}")
    public ResponseEntity<?> deleteSurveyRequestById(@PathVariable("surveyId") int surveyId) {
        ApartmentSurveyRequest request = requestService.getSurveyRequestById(surveyId);
        if (request == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + surveyId, HttpStatus.NOT_FOUND);
        }

        // Xóa tất cả các detail survey liên quan đến survey này
        List<ApartmentDetailRequest> detailRequests = detailRequestService.getAllDetailRequestByRequestID(surveyId);
        for (ApartmentDetailRequest detailRequest : detailRequests) {
            detailRequestService.deleteDetailRequest(detailRequest.getId());
        }

        // Sau đó, xóa survey chính
        requestService.deleteSurveyRequestById(surveyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping(path = "/admin/survey_request/add/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addSurveyRequest(@RequestBody Map<String, Object> params) throws ParseException {
        try {
            // Lấy thông tin từ request body
            String startDateStr = (String) params.get("startDate");
            String endDateStr = (String) params.get("endDate");
            
            if (endDateStr == null) {
                return new ResponseEntity<>("One or more parameters are missing or null", HttpStatus.BAD_REQUEST);
            }
            
            ApartmentUser user = userService.getUsersByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user == null || !user.getRole().equals(ApartmentUser.ADMIN)) {
                return new ResponseEntity<>("invalid Admin", HttpStatus.NOT_FOUND);
            }
            Date startDate = startDateStr!=null ? StringUtil.dateFormater().parse(startDateStr) : new Date();
            Date endDate = StringUtil.dateFormater().parse(endDateStr);

            // Kiểm tra nếu ngày bắt đầu lớn hơn ngày kết thúc
            if (startDate.after(endDate)) {
                return new ResponseEntity<>("Start date cannot be after end date", HttpStatus.BAD_REQUEST);
            }
            if (startDateStr!=null && startDate.before(new Date()))
                 return new ResponseEntity<>("Start date cannot be before today", HttpStatus.BAD_REQUEST);

            // Tạo đối tượng ApartmentSurveyRequest
            
            ApartmentSurveyRequest surveyRequest = new ApartmentSurveyRequest();
            surveyRequest.setAdminId(user.getApartmentAdmin());
            surveyRequest.setStartDate(startDate);
            surveyRequest.setEndDate(endDate);
            surveyRequest.setIsActive((short) 1);

            // Thêm một survey request
            requestService.addSurveyRequest(surveyRequest);
            List<Map<String,String>> questions = (List<Map<String,String>>) params.get("questions");
            questions.forEach(x -> {
                ApartmentDetailRequest temp = new ApartmentDetailRequest();
                temp.setQuestion(x.get("question"));
                temp.setRequestId(surveyRequest);
                ApartmentDetailRequest.ScoreBand band = ApartmentDetailRequest.ScoreBand.get(x.get("band"));
                temp.setScoreBand(band != null ? band.toString() : ApartmentDetailRequest.ScoreBand.BAND_5.toString());
                detailRequestService.addDetailRequest(temp);
            });
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về lỗi nếu có
            return new ResponseEntity<>("Failed to create survey request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/api/survey_request/{id}", produces = "application/json")
    public ResponseEntity<Object> updateSurveyRequestById(@PathVariable int id,
                                                          @RequestBody Map<String, String> updates) {
        ApartmentSurveyRequest surveyRequest = requestService.getSurveyRequestById(id);
        if (surveyRequest == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case "isActive":
                    surveyRequest.setIsActive(Short.parseShort(value));
                    break;
                case "adminId":
                    int adminId = Integer.parseInt(value);
                    ApartmentUser user = userService.getUserByID(adminId);
                    if (user == null) {
                        return new ResponseEntity<>("Can not find admin with Id: " + adminId, HttpStatus.NOT_FOUND);
                    }
                    surveyRequest.setAdminId(user.getApartmentAdmin());
                    break;
                case "endDate":
                    try {
                        Date endDate = StringUtil.dateFormater().parse(value);
                        if (surveyRequest.getStartDate() != null && endDate.before(surveyRequest.getStartDate())) {
                            throw new IllegalArgumentException("End date cannot be before start date");
                        }
                        surveyRequest.setEndDate(endDate);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid end date format");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Trường cập nhật không hợp lệ: " + key);
            }

        }
        requestService.updateSurveyRequest(surveyRequest);
        return ResponseEntity.ok(surveyRequest);
    }
    
    

}
