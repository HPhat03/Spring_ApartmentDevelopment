package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentReport;

import java.util.List;
import java.util.Map;

public interface ReportService {
     List<ApartmentReport> getAllReportByApartmentId(int id, int page) ;
    public List<ApartmentReport> getAllReport(Map<String, String> params) ;
//    List<ApartmentReport> getAllReportByApartmentId(int id) ;

    void deleteReportById(int id) ;

    void addReport(ApartmentReport report) ;

    void updateReport(ApartmentReport report) ;

    ApartmentReport getReportById(int id) ;
    public long countReport() ;

}
