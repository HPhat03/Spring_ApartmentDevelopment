package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentUser;

import java.util.List;
import java.util.Map;

public interface SurveyRequestService {
    public List<ApartmentSurveyRequest> getAllSurveyRequest(Map<String, String> params)  ;

    ApartmentSurveyRequest getSurveyRequestById(int id) ;

    void deleteSurveyRequestById(int id) ;

    void addSurveyRequest(ApartmentSurveyRequest request) ;

    void updateSurveyRequest(ApartmentSurveyRequest surveyRequest) ;
    public long countSurvey() ;
}
