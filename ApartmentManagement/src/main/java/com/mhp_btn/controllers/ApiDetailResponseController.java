package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import com.mhp_btn.services.DetailRequestService;
import com.mhp_btn.services.DetailResponseService;
import com.mhp_btn.services.SurveyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiDetailResponseController {
    @Autowired
    private DetailResponseService detailResService;
    @Autowired
    private SurveyResponseService responseService;
    @Autowired
    private DetailRequestService detailRequestService;

    @GetMapping(path="survey_response/{responseId}/details", produces = "application/json")
    public ResponseEntity<?> getDetailResponseByResponseId(@PathVariable int responseId) {
        ApartmentSurveyResponse surveyResponse = responseService.getSurveyResponseById(responseId);
        if (surveyResponse == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + responseId, HttpStatus.NOT_FOUND);
        }
        List<ApartmentDetailResponse> detailResponses = detailResService.getAllDetailResponseByResponseID(responseId);

        return new ResponseEntity<>(detailResponses, HttpStatus.OK);
    }

    @DeleteMapping(path = "/survey_response/{responseId}/details/{detailId}")
    public ResponseEntity<?> deleteDetailResponseById(@PathVariable("responseId") int responseId,
                                                     @PathVariable("detailId") int detailId) {
        ApartmentSurveyResponse surveyResponse = responseService.getSurveyResponseById(responseId);
        if (surveyResponse == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + responseId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailResponse response = detailResService.getDetailResponseById(detailId);
        if (response == null) {
            return new ResponseEntity<>("Detail response not found with ID: " + detailId, HttpStatus.NOT_FOUND);
        }
        detailResService.deleteDetailResponse(detailId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


    @PostMapping("/survey_response/{responseId}/details")
    public ResponseEntity<?> createDetailResponse(@PathVariable("responseId") int responseId,
                                                           @RequestBody Map<String, String> params) {
        // Kiểm tra xem các trường dữ liệu cần thiết đã được gửi lên hay không
        if (!params.containsKey("answer")  || !params.containsKey("questionId")) {
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
        }
        ApartmentSurveyResponse surveyResponse = responseService.getSurveyResponseById(responseId);
        if (surveyResponse == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + responseId, HttpStatus.NOT_FOUND);
        }
        int questionId = Integer.parseInt(params.get("questionId"));
        ApartmentDetailRequest detailRequest = detailRequestService.getDetailRequestById(questionId);
        if(detailRequest == null){
            return new ResponseEntity<>("detail request not found with ID: " + questionId, HttpStatus.NOT_FOUND);
        }
        int answer = Integer.parseInt(params.get("answer"));

        ApartmentDetailResponse detailResponse = new ApartmentDetailResponse();
        detailResponse.setResponseId(surveyResponse);
        detailResponse.setQuestionId(detailRequest);
        detailResponse.setAnswer(answer);

        detailResService.addDetailResponse(detailResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value="/survey_response/{responseId}/details/{detailId}" , produces = "application/json")
    public ResponseEntity<?> updateDetailResponse(@PathVariable("responseId") int responseId,
                                                 @PathVariable("detailId") int detailId,
                                                 @RequestBody Map<String, String> updates) {
        ApartmentSurveyResponse surveyResponse = responseService.getSurveyResponseById(responseId);
        if (surveyResponse == null) {
            return new ResponseEntity<>("Survey request not found with ID: " + responseId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailResponse response = detailResService.getDetailResponseById(detailId);
        if (response == null) {
            return new ResponseEntity<>("Detail response not found with ID: " + detailId, HttpStatus.NOT_FOUND);
        }
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case "answer":
                    response.setAnswer(Integer.parseInt(value));
                    break;
                case "questionId":
                    int questionId = Integer.parseInt(value);
                    ApartmentDetailRequest detailRequest = detailRequestService.getDetailRequestById(questionId);
                    if(detailRequest == null){
                        return new ResponseEntity<>("detail request not found with ID: " + questionId, HttpStatus.NOT_FOUND);
                    }
                    response.setQuestionId(detailRequest);
                    break;
                default:
                    throw new IllegalArgumentException("Trường cập nhật không hợp lệ: " + key);
            }

        }
        detailResService.updateDetailResponse(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
