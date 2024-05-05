package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentDetailResponse;

import java.util.List;

public interface DetailResponseService {
    List<ApartmentDetailResponse> getAllDetailResponseByResponseID(int id) ;

    ApartmentDetailResponse getDetailResponseById(int id) ;

    public void deleteDetailResponse(int id) ;
    void addDetailResponse(ApartmentDetailResponse response) ;
    void updateDetailResponse(ApartmentDetailResponse response) ;
}
