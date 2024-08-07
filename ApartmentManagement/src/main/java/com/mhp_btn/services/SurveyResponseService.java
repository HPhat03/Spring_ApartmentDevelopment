package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentSurveyResponse;

import java.util.List;

public interface SurveyResponseService {
    List<ApartmentSurveyResponse> getAllSurveyResponse() ;

    ApartmentSurveyResponse getSurveyResponseById(int id) ;
    public List<ApartmentSurveyResponse> getAllBySurveyId(Integer surveyId) ;

    void deleteSurveyResponseById(int id) ;

    void addSurveyResponse(ApartmentSurveyResponse response) ;

    void updateSurveyResponse(ApartmentSurveyResponse response) ;
}
