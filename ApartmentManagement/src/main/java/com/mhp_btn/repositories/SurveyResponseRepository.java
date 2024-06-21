package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentSurveyResponse;

import java.util.List;

public interface SurveyResponseRepository {

    List<ApartmentSurveyResponse> getAllSurveyResponse();
    ApartmentSurveyResponse getSurveyResponseById(int id);
    List<ApartmentSurveyResponse> getAllBySurveyId(Integer surveyId) ;

    void deleteSurveyResponseById(int id);

    void  addSurveyResponse(ApartmentSurveyResponse response);

    void updateSurveyResponse(ApartmentSurveyResponse response);
}
