package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentServiceConstract;

import java.util.List;

public interface ServiceConstractService {

    List<ApartmentServiceConstract> getAllServiceConstract() ;

    ApartmentServiceConstract getServiceConstractById(int id) ;

    void deleteServiceConstractById(int id) ;

    void addServiceConstract(ApartmentServiceConstract constract) ;

    void updateServiceConstract(ApartmentServiceConstract constract) ;

    public List<ApartmentService> getServicesByApartmentId(int apartmentId) ;
    public void deleteServiceConstractByApartmentId(int apartmentId) ;
}
