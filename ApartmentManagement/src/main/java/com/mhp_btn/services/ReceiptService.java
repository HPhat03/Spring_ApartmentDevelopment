package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentReceipt;

import java.util.List;

public interface ReceiptService {

    List<ApartmentReceipt> getAllReceipt() ;

    ApartmentReceipt getReceiptById(int id) ;

    void deleteReiptById(int id);

    void addReceipt(ApartmentReceipt receipt) ;

    void updateReceipt(ApartmentReceipt receipt) ;
}
