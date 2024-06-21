package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentDetailReport;

import java.util.List;

public interface DetailReportRepository {

    List<ApartmentDetailReport> getDetailReportByReportId(int id) ;


    void addDetailReport(ApartmentDetailReport newDetail);

    ApartmentDetailReport getDetailReportById(int id);

    void updateDetailReport(ApartmentDetailReport detailReport);

    void deteleDetailReport(ApartmentDetailReport detailReport);
}
