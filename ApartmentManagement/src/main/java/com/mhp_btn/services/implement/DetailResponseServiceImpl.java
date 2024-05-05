package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.repositories.DetailResponseRepository;
import com.mhp_btn.services.DetailResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailResponseServiceImpl implements DetailResponseService {
    @Autowired
    private DetailResponseRepository detailResponseRepo;

    @Override
    public List<ApartmentDetailResponse> getAllDetailResponseByResponseID(int id) {
        return this.detailResponseRepo.getAllDetailResponseByResponseID(id);
    }

    @Override
    public ApartmentDetailResponse getDetailResponseById(int id) {
        return this.detailResponseRepo.getDetailResponseById(id);
    }

    @Override
    public void deleteDetailResponse(int id) {
        this.detailResponseRepo.deleteDetailResponse(id);
    }

    @Override
    public void addDetailResponse(ApartmentDetailResponse response) {
        this.detailResponseRepo.addDetailResponse(response);
    }

    @Override
    public void updateDetailResponse(ApartmentDetailResponse response) {
        this.detailResponseRepo.updateDetailResponse(response);
    }
}
