/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.repositories.ResidentRepository;
import com.mhp_btn.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ResidentServiceImpl implements ResidentService {
    @Autowired
    private ResidentRepository ar;
    
    @Override
    public void save(ApartmentResident resident) {
        this.ar.save(resident);
    }

    @Override
    public ApartmentResident getResidentById(int id) {
        return this.ar.getResidentById(id);
    }

    @Override
    public void update(ApartmentResident resident) {
        this.ar.update(resident);
    }

}
