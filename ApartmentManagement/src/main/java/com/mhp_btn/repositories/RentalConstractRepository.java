package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRentalConstract;

import java.util.List;

public interface RentalConstractRepository {
    public List<ApartmentRentalConstract> getAllRentalConstract() ;

    void deleteRentalConstractById(int id);

    ApartmentRentalConstract getRentalConstractById(int id);

    void addRentalConstract(ApartmentRentalConstract constract);
}
