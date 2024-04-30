package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.repositories.FloorRepository;
import com.mhp_btn.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorRepository floorRepo;
    @Override
    public ApartmentFloor getFloorById(int id) {
        try {
            return  this.floorRepo.getFloorById(id);
            // Code to get product by ID
        } catch (Exception e) {
            System.out.println("Error occurred while getting product by ID: " + e.getMessage());
        }
        return null;
    }
}
