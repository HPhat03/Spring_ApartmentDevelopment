/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentPaidPicture;
import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.repositories.PaymentRepository;
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
public class PaymentRepositoryImpl implements PaymentRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public void addOrUpdate(ApartmentPayment p) {
        Session s = this.factory.getObject().getCurrentSession();
        if(p.getId() != null)
            s.update(p);
        else
            s.save(p);
    }

    @Override
    public void addPictureMoMo(ApartmentPaidPicture p) {
        Session s = this.factory.getObject().getCurrentSession();
        if(p.getId() > 0)
            s.update(p);
        else
            s.save(p);
    }
    
}
