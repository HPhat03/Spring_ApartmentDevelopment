package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentDetailReport;

public interface DetailReportRepository {

    ApartmentDetailReport getDetailReportByReportId(int id) ;

    void addDetailReport(ApartmentDetailReport newDetail);

    ApartmentDetailReport getDetailReportById(int id);

    void updateDetailReport(ApartmentDetailReport detailReport);

    void deteleDetailReport(ApartmentDetailReport detailReport);
}
