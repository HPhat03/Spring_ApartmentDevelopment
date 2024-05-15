package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.repositories.DetailRequestRepository;
import com.mhp_btn.services.DetailRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailRequestServiceImpl implements DetailRequestService {
    @Autowired
    private DetailRequestRepository detailRequestRepo;

    @Override
    public List<ApartmentDetailRequest> getAllDetailRequestByRequestID(int id) {
        return this.detailRequestRepo.getAllDetailRequestByRequestID(id);
    }

    @Override
    public ApartmentDetailRequest getDetailRequestById(int id) {
        return this.detailRequestRepo.getDetailRequestById(id);
    }

    @Override
    public void deleteDetailRequest(int id) {
        this.detailRequestRepo.deleteDetailRequest(id);
    }

    @Override
    public void addDetailRequest(ApartmentDetailRequest request) {
        this.detailRequestRepo.addDetailRequest(request);
    }

    @Override
    public void updateDetailRequest(ApartmentDetailRequest request) {
        this.detailRequestRepo.updateDetailRequest(request);
    }
}
