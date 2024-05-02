package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentReceipt;

import java.util.List;

public interface DetailReceiptRepository {

    // lay chi tiet hoa don theo ID hoa don
    List<ApartmentDetailReceipt> getDetailReceiptsByReceiptId(int id);

    //tao detail receipt theo id receipt
    void saveDetailReceipt(ApartmentDetailReceipt detailReceipt);
}
