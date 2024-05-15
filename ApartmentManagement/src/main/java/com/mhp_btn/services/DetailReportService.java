package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentDetailReport;

public interface DetailReportService {

    ApartmentDetailReport getDetailReportByReportId(int id) ;

    void addDetailReport(ApartmentDetailReport newDetail) ;

    ApartmentDetailReport getDetailReportById(int id) ;

    void updateDetailReport(ApartmentDetailReport detailReport) ;

    void deteleDetailReport(ApartmentDetailReport detailReport) ;
}
