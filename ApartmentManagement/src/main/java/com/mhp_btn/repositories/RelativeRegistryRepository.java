package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRelativeRegistry;

import java.util.List;

public interface RelativeRegistryRepository {
    List<ApartmentRelativeRegistry> getAllRelativeRegistry();

    ApartmentRelativeRegistry getRelativeRegistryById(int id);

    void deleteRelativeRegistryById(int id);

    void addRelativeRegistry(ApartmentRelativeRegistry relativeRegistry);

    void updateRelativeRegistry(ApartmentRelativeRegistry relativeRegistry);
}
