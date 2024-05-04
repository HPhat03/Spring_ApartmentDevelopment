package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentSmartCabinet;

import java.util.List;

public interface SmartCabinetService {

    List<ApartmentSmartCabinet> getAllSmartCabinets() ;

    List<ApartmentSmartCabinet> getAllSmartCabinetByApartmentId(int id) ;

    ApartmentSmartCabinet getSmartCabinetById(int id) ;

    void deleteCabinetById(int id) ;

    void addCabinet(ApartmentSmartCabinet cabinet);

    void updateCabinet(ApartmentSmartCabinet cabinet) ;

}
