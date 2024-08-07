/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.repositories.ServiceRepository;
import com.mhp_btn.services.ServiceService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ServiceServiceImpl implements  ServiceService{
    
    @Autowired
    private ServiceRepository repo;


    @Override
    public List<ApartmentService> getService(Map<String, String> params) {
        return this.repo.getService(params);
    }

    @Override
    public void addOrUpdate(ApartmentService service) {
        this.repo.addOrUpdate(service);
    }

    @Override
    public List<ApartmentService> getServiceActive() {
        return this.repo.getServiceActive();
    }

    @Override
    public List<ApartmentService> getAllServices() {
        return this.repo.getAllServices();
    }


    @Override
    public ApartmentService getServiceById(int id) {
        return this.repo.getServiceById(id);
    }

    @Override
    public void deleteServiceById(int id) {
        this.repo.deleteServiceById(id);
    }

    @Override
    public void updateervice(ApartmentService service) {
        this.repo.updateService(service);
    }

    @Override
    public long countService() {
        return this.repo.countService();
    }


}
