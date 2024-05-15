package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentFloor;

import java.util.List;

public interface FloorService {
    ApartmentFloor getFloorById(int id);

    List<ApartmentFloor> getAllFloor();

    void addFloor(ApartmentFloor floor);

    void deleteFloorById(int id);
    void updateFloor(ApartmentFloor floor);
}