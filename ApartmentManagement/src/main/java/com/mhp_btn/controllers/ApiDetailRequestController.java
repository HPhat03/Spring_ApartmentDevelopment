package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.services.DetailRequestService;
import com.mhp_btn.services.SurveyRequestService;
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
public class ApiDetailRequestController {
    @Autowired
    private DetailRequestService detailService;
    @Autowired
    private SurveyRequestService requestService;

    //Lấy tất cả chi tiết theo request id
    @GetMapping(path="/api/survey_request/{requestId}/details/", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<?> getDetailReportByReportId(@PathVariable int requestId) {
        // kiem tra su ton tai cua id request
        List<ApartmentDetailRequest> detailRequest = detailService.getAllDetailRequestByRequestID(requestId);
        if (detailRequest == null || detailRequest.isEmpty()) {
            return new ResponseEntity<>("Không tìm thấy khảo sát hoặc khảo sát trống với mã: " + requestId, HttpStatus.OK);
        }
        return new ResponseEntity<>(detailRequest, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/survey_request/{surveyId}/details/{detailId}")
    public ResponseEntity<?> deleteDetailRequestById(@PathVariable("surveyId") int surveyId,
                                                       @PathVariable("detailId") int detailId) {
        // Kiểm tra xem căn hộ có tồn tại không
        ApartmentSurveyRequest surveyRequest = requestService.getSurveyRequestById(surveyId);
        if (surveyRequest == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + surveyId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailRequest request = detailService.getDetailRequestById(detailId);
        if (request == null) {
            return new ResponseEntity<>("Detail request not found with ID: " + detailId, HttpStatus.NOT_FOUND);
        }

        detailService.deleteDetailRequest(detailId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/survey_request/{surveyId}/details")
    public ResponseEntity<?> createDetailRequestBySurveyId(@PathVariable("surveyId") int surveyId,
                                                           @RequestBody Map<String, String> params) {
        // Kiểm tra xem các trường dữ liệu cần thiết đã được gửi lên hay không
        if (!params.containsKey("question") || !params.containsKey("scoreBand")) {
            return new ResponseEntity<>("Missing required fields: 'question' and/or 'scoreBand'", HttpStatus.BAD_REQUEST);
        }
        ApartmentSurveyRequest surveyRequest = requestService.getSurveyRequestById(surveyId);
        if (surveyRequest == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + surveyId, HttpStatus.NOT_FOUND);
        }

        String question = params.get("question");
        String scoreBand = params.get("scoreBand");

        if (question == null || question.isEmpty() || scoreBand == null || scoreBand.isEmpty()) {
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
        }

        ApartmentDetailRequest detailRequest = new ApartmentDetailRequest();
        detailRequest.setQuestion(question);
        detailRequest.setScoreBand(scoreBand);
        detailRequest.setRequestId(surveyRequest);

        detailService.addDetailRequest(detailRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping(value="/survey_request/{surveyId}/details/{detailId}" , produces = "application/json")
    public ResponseEntity<?> updateDetailRequest(@PathVariable("surveyId") int surveyId,
                                                    @PathVariable("detailId") int detailId,
                                                    @RequestBody Map<String, String> updates) {
        ApartmentSurveyRequest surveyRequest = requestService.getSurveyRequestById(surveyId);
        if (surveyRequest == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + surveyId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailRequest request = detailService.getDetailRequestById(detailId);
        if (request == null) {
            return new ResponseEntity<>("Detail request not found with ID: " + detailId, HttpStatus.NOT_FOUND);
        }
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case "question":
                    request.setQuestion(value);
                    break;
                case "scoreBand":
                    request.setScoreBand(value);
                    break;
                default:
                    throw new IllegalArgumentException("Trường cập nhật không hợp lệ: " + key);
            }

        }
        detailService.updateDetailRequest(request);

        return new ResponseEntity<>(request, HttpStatus.OK);
    }


}
