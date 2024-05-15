/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentService;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ServiceService {
    List<ApartmentService> getServices();
    void addService(ApartmentService service);

    public ApartmentService getServiceById(int id);

    void deleteServiceById(int id);

    void updateervice(ApartmentService service);
}
