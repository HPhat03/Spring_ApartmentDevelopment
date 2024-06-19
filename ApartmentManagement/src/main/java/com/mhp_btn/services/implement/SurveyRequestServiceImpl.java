package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.repositories.SurveyRequestRepository;
import com.mhp_btn.services.SurveyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SurveyRequestServiceImpl implements SurveyRequestService {
    @Autowired
    private SurveyRequestRepository requestRepo;

    @Override
    public List<ApartmentSurveyRequest> getAllSurveyRequest(Map<String, String> params)  {
        return this.requestRepo.getAllSurveyRequest(params);
    }

    @Override
    public ApartmentSurveyRequest getSurveyRequestById(int id) {
        return this.requestRepo.getSurveyRequestById(id);
    }

    @Override
    public void deleteSurveyRequestById(int id) {
        this.requestRepo.deleteSurveyRequestById(id);
    }

    @Override
    public void addSurveyRequest(ApartmentSurveyRequest request) {
        this.requestRepo.addSurveyRequest(request);
    }

    @Override
    public void updateSurveyRequest(ApartmentSurveyRequest surveyRequest) {
        this.requestRepo.updateSurveyRequest(surveyRequest);
    }

    @Override
    public long countSurvey() {
        return this.requestRepo.countSurvey();
    }


}
