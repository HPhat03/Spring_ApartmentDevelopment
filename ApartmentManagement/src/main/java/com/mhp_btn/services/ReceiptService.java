package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentReceipt;

import java.util.List;
import java.util.HashMap;

public interface ReceiptService {

    List<ApartmentReceipt> getAllReceipt(HashMap<String, String> params) ;

    ApartmentReceipt getReceiptById(int id) ;

    void deleteReiptById(int id);

    void addReceipt(ApartmentReceipt receipt) ;

    void updateReceipt(ApartmentReceipt receipt) ;
}
