package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentDetailResponse;

import java.util.List;

public interface DetailResponseRepository {
    List<ApartmentDetailResponse> getAllDetailResponseByResponseID(int id);
    ApartmentDetailResponse getDetailResponseById(int id);

    void  deleteDetailResponse(int id);
    void addDetailResponse(ApartmentDetailResponse response);
    void updateDetailResponse(ApartmentDetailResponse response);
}
