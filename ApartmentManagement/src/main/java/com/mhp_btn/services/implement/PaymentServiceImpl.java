/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentPaidPicture;
import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.repositories.PaymentRepository;
import com.mhp_btn.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository repo;
    @Override
    public void SaveOrUpdate(ApartmentPayment p) {
        this.repo.addOrUpdate(p);
    }

    @Override
    public void addPictureMoMo(ApartmentPaidPicture p) {
        this.repo.addPictureMoMo(p);
    }

    @Override
    public ApartmentPayment getPaymentByReceiptID(int id) {
        return this.getPaymentByReceiptID(id);
    }
    
}
