package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRoom;

import java.util.List;

public interface FloorRepository {
    ApartmentFloor getFloorById(int id);
    List<ApartmentFloor> getAllFloor();

    void addFloor(ApartmentFloor floor);

    void deleteFloorById(int id);

    void updateFloor(ApartmentFloor floor);
}
