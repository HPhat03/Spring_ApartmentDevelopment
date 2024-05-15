package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRentalConstract;

import java.util.List;

public interface ReceiptRepository {

    List<ApartmentReceipt> getAllReceipt();

    ApartmentReceipt getReceiptById(int id);

    void deleteReceiptById(int id);

    void addReceipt(ApartmentReceipt receipt);

    void updateReceipt(ApartmentReceipt receipt);
}
