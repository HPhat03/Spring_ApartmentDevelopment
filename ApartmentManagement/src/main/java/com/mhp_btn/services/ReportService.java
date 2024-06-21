package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentReport;

import java.util.List;

public interface ReportService {
    // List<ApartmentReport> getAllReportByApartmentId(int id, int page) ;
    public List<ApartmentReport> getAllReport() ;
    List<ApartmentReport> getAllReportByApartmentId(int id) ;

    void deleteReportById(int id) ;

    void addReport(ApartmentReport report) ;

    void updateReport(ApartmentReport report) ;

    ApartmentReport getReportById(int id) ;

}
