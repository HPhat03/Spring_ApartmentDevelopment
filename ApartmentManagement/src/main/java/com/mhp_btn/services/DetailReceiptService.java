package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentReceipt;

import java.util.List;

public interface DetailReceiptService {
    List<ApartmentDetailReceipt> getDetailReceiptsByReceiptId(int id);

    void createDetailByReceiptId( ApartmentDetailReceipt detailReceipt);

    void deleteDetailReceiptById(int id) ;

    public ApartmentDetailReceipt getDetailReceiptById(int id) ;

    void updateDetailReceipt(ApartmentDetailReceipt detailReceipt);
}
