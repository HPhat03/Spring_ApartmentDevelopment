/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.repositories.ResidentRepository;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ResidentRepositoryImpl implements ResidentRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void save(ApartmentResident resident) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(resident);
    }
    
}
