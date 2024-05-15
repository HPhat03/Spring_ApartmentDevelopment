package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentRelativeRegistry;

import java.util.List;

public interface RelativeRegistryService {
    List<ApartmentRelativeRegistry> getAllRelativeRegistry();

    ApartmentRelativeRegistry getRelativeRegistryById(int id) ;

    void deleteReceiptById(int id) ;

    void addRelativeRegistry(ApartmentRelativeRegistry relativeRegistry);

    void updateRelativeRegistry(ApartmentRelativeRegistry relativeRegistry) ;
}
