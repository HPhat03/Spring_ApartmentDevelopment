package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

public interface SmartCabinetRepository {

    List<ApartmentSmartCabinet> getAllSmartCabinets(Map<String, String> params) ;

    List<ApartmentSmartCabinet> getAllSmartCabinetByApartmentId(int id);

    ApartmentSmartCabinet getSmartCabinetById(int id);

    void deleteCabinetById(int id);

    void addCabinet(ApartmentSmartCabinet cabinet);

    void updateCabinet(ApartmentSmartCabinet cabinet);

    public void addOrUpdate(ApartmentSmartCabinet c) ;

    public long countCabinets() ;
}
