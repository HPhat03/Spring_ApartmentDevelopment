package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRoom;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


public interface ReceiptRepository {

    List<ApartmentReceipt> getAllReceipt(HashMap<String, String> params);

    ApartmentReceipt getReceiptById(int id);

    void deleteReceiptById(int id);

    void addReceipt(ApartmentReceipt receipt);

    void updateReceipt(ApartmentReceipt receipt);
   List<ApartmentReceipt> getAll(Map<String, String> params) ;
    public long countReceipt() ;

}
