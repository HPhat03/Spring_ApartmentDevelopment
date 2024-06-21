package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentUser;

import java.util.List;

public interface SurveyRequestService {
    List<ApartmentSurveyRequest> getAllSurveyRequest(int page) ;

    ApartmentSurveyRequest getSurveyRequestById(int id) ;

    void deleteSurveyRequestById(int id) ;

    void addSurveyRequest(ApartmentSurveyRequest request) ;

    void updateSurveyRequest(ApartmentSurveyRequest surveyRequest) ;
}
