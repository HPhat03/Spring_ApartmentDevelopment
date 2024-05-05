package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentDetailRequest;

import java.util.List;

public interface DetailRequestRepository {
    List<ApartmentDetailRequest> getAllDetailRequestByRequestID(int id);

    ApartmentDetailRequest getDetailRequestById(int id);

    void deleteDetailRequest(int id);

    void addDetailRequest(ApartmentDetailRequest request);

    void updateDetailRequest(ApartmentDetailRequest request);
}
