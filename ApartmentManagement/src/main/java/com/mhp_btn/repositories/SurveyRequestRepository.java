package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentSurveyRequest;

import java.util.List;

public interface SurveyRequestRepository {

    List<ApartmentSurveyRequest> getAllSurveyRequest();

    ApartmentSurveyRequest getSurveyRequestById(int id);

    void deleteSurveyRequestById(int id);

    void addSurveyRequest(ApartmentSurveyRequest request);

    void updateSurveyRequest(ApartmentSurveyRequest surveyRequest);
}
