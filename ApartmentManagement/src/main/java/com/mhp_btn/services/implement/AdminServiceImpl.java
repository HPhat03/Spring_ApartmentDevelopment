/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.repositories.AdminRepository;
import com.mhp_btn.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository ar;
    
    @Override
    public void save(ApartmentAdmin admin) {
        this.ar.save(admin);
    }
    
}
