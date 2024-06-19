package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentSurveyResponse;
import com.mhp_btn.repositories.SurveyResponseRepository;
import com.mhp_btn.services.SurveyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyResponseServiceImpl implements SurveyResponseService {
    @Autowired
    private SurveyResponseRepository responseRepo;

    @Override
    public List<ApartmentSurveyResponse> getAllSurveyResponse() {
        return this.responseRepo.getAllSurveyResponse();
    }

    @Override
    public ApartmentSurveyResponse getSurveyResponseById(int id) {
        return this.responseRepo.getSurveyResponseById(id);
    }

    @Override
    public List<ApartmentSurveyResponse> getAllBySurveyId(Integer surveyId) {
        return this.responseRepo.getAllBySurveyId(surveyId);
    }

    @Override
    public void deleteSurveyResponseById(int id) {
        this.responseRepo.deleteSurveyResponseById(id);
    }

    @Override
    public void addSurveyResponse(ApartmentSurveyResponse response) {
        this.responseRepo.addSurveyResponse(response);
    }

    @Override
    public void updateSurveyResponse(ApartmentSurveyResponse response) {
        this.responseRepo.updateSurveyResponse(response);
    }
}
