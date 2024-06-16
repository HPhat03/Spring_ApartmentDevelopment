/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ServiceRepository {
    List<ApartmentService> getService(Map<String, String> params) ;
    List<ApartmentService> getServiceActive() ;
    List<ApartmentService> getAllServices();
        void addOrUpdate(ApartmentService service);
    public ApartmentService getServiceById(int id) ;

    void deleteServiceById(int id);
    void updateService(ApartmentService service);
    long countService();
}
