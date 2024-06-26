package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentSmartCabinet;

import java.util.List;
import java.util.Map;

public interface SmartCabinetService {

    List<ApartmentSmartCabinet> getAllSmartCabinets(Map<String, String> params) ;

    List<ApartmentSmartCabinet> getAllSmartCabinetByApartmentId(int id) ;

    ApartmentSmartCabinet getSmartCabinetById(int id) ;

    void deleteCabinetById(int id) ;

    void addCabinet(ApartmentSmartCabinet cabinet);

    void updateCabinet(ApartmentSmartCabinet cabinet) ;
    public void addOrUpdate(ApartmentSmartCabinet c) ;
    public long countCabinets() ;

    }
