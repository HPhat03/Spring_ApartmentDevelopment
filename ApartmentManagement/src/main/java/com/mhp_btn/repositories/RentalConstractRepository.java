package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRentalConstract;

import java.util.List;
import java.util.Map;

public interface RentalConstractRepository {
    public List<ApartmentRentalConstract> getAllRentalConstract(Map<String, String> params) ;

    void deleteRentalConstractById(int id);

    ApartmentRentalConstract getRentalConstractById(int id);

    void addRentalConstract(ApartmentRentalConstract constract);

    ApartmentRentalConstract getConstractById(int id);

    void updateConstract(ApartmentRentalConstract constract);
}
