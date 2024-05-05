package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentDetailRequest;

import java.util.List;

public interface DetailRequestService {

    List<ApartmentDetailRequest> getAllDetailRequestByRequestID(int id) ;

    ApartmentDetailRequest getDetailRequestById(int id) ;

    void deleteDetailRequest(int id) ;
    void addDetailRequest(ApartmentDetailRequest request) ;

    void updateDetailRequest(ApartmentDetailRequest request) ;
}
