package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentRentalConstract;

import java.util.List;

public interface RentalConstractService {

    List<ApartmentRentalConstract> getAllRentalConstract();

    public void deleteRentalConstractById(int id);

    ApartmentRentalConstract getRentalConstractById(int id);

    void addRentalConstract(ApartmentRentalConstract constract);

    ApartmentRentalConstract getConstractById(int id);

    void updateConstract(ApartmentRentalConstract constract);
}