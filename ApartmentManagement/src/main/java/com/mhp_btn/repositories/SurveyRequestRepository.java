package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentSurveyRequest;

import java.util.List;
import java.util.Map;

public interface SurveyRequestRepository {

    // List<ApartmentSurveyRequest> getAllSurveyRequest(int page);

    List<ApartmentSurveyRequest> getAllSurveyRequest(Map<String, String> params) ;
    ApartmentSurveyRequest getSurveyRequestById(int id);

    void deleteSurveyRequestById(int id);

    void addSurveyRequest(ApartmentSurveyRequest request);

    void updateSurveyRequest(ApartmentSurveyRequest surveyRequest);
    long countSurvey();
}
