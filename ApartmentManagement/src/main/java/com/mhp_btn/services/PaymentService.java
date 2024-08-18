/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentPaidPicture;
import com.mhp_btn.pojo.ApartmentPayment;

/**
 *
 * @author Admin
 */
public interface PaymentService {
    void SaveOrUpdate(ApartmentPayment p);
    void addPictureMoMo(ApartmentPaidPicture p);
    public ApartmentPayment getPaymentByReceiptID(int id);
}
