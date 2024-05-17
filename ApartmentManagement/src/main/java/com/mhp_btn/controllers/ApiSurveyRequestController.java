package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentUser;
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

@RestController
@RequestMapping("/api")
public class ApiSurveyRequestController {
    @Autowired
    private SurveyRequestService requestService;
    @Autowired
    private UserService userService;

    //Lay tat ca khao sat
    @GetMapping(path = "/survey_request", produces = "application/json")
    public ResponseEntity<List<ApartmentSurveyRequest>> getAllSurveyRequest() {
        List<ApartmentSurveyRequest> surveyRequests = requestService.getAllSurveyRequest();
        return new ResponseEntity<>(surveyRequests, HttpStatus.OK);
    }

    @DeleteMapping(path = "/survey_request/{surveyId}")
    public ResponseEntity<?> deleteSurveyRequestById(@PathVariable("surveyId") int surveyId) {
        ApartmentSurveyRequest request = requestService.getSurveyRequestById(surveyId);
        if (request == null) {
            return new ResponseEntity<>("survey request not found with ID: " + surveyId, HttpStatus.NOT_FOUND);
        }
        requestService.deleteSurveyRequestById(surveyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/survey_request")
    public ResponseEntity<?> addSurveyRequest(@RequestBody Map<String, String> params) {
        try {
            // Lấy thông tin từ request body
            String adminIdStr = params.get("adminId");
            String startDateStr = params.get("startDate");
            String endDateStr = params.get("endDate");
            String isActiveStr = params.get("isActive");

            if (adminIdStr == null || startDateStr == null || endDateStr == null || isActiveStr == null) {
                return new ResponseEntity<>("One or more parameters are missing or null", HttpStatus.BAD_REQUEST);
            }

            int adminId = Integer.parseInt(adminIdStr);
            ApartmentUser user = userService.getUserByID(adminId);
            if (user == null) {
                return new ResponseEntity<>("Can not find admin with Id: " + adminId, HttpStatus.NOT_FOUND);
            }

            Date startDate = StringUtil.dateFormater().parse(startDateStr);
            Date endDate = StringUtil.dateFormater().parse(endDateStr);

            // Kiểm tra nếu ngày bắt đầu lớn hơn ngày kết thúc
            if (startDate.after(endDate)) {
                return new ResponseEntity<>("Start date cannot be after end date", HttpStatus.BAD_REQUEST);
            }

            int isActive = Integer.parseInt(isActiveStr);

            // Tạo đối tượng ApartmentSurveyRequest
            ApartmentSurveyRequest surveyRequest = new ApartmentSurveyRequest();
            surveyRequest.setAdminId(user.getApartmentAdmin());
            surveyRequest.setStartDate(startDate);
            surveyRequest.setEndDate(endDate);
            surveyRequest.setIsActive((short) isActive);

            // Lưu đối tượng vào cơ sở dữ liệu
            requestService.addSurveyRequest(surveyRequest);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về lỗi nếu có
            return new ResponseEntity<>("Failed to create survey request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/survey_request/{id}", produces = "application/json")
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
                case "startDate":
                    try {
                        Date startDate = StringUtil.dateFormater().parse(value);
                        if (surveyRequest.getEndDate() != null && startDate.after(surveyRequest.getEndDate())) {
                            throw new IllegalArgumentException("Start date cannot be after end date");
                        }
                        surveyRequest.setStartDate(startDate);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid start date format");
                    }
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
