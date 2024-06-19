package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentDetailReport;
import com.mhp_btn.repositories.DetailReportRepository;
import com.mhp_btn.services.DetailReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailReportServiceImpl implements DetailReportService {
    @Autowired
    private DetailReportRepository reportRepo;

    @Override
    public List<ApartmentDetailReport> getDetailReportByReportId(int id) {
        return this.reportRepo.getDetailReportByReportId(id);
    }

    @Override
    public void addDetailReport(ApartmentDetailReport newDetail) {
        this.reportRepo.addDetailReport(newDetail);
    }

    @Override
    public ApartmentDetailReport getDetailReportById(int id) {
        return this.reportRepo.getDetailReportById(id);
    }

    @Override
    public void updateDetailReport(ApartmentDetailReport detailReport) {
        this.reportRepo.updateDetailReport(detailReport);
    }

    @Override
    public void deteleDetailReport(ApartmentDetailReport detailReport) {
        this.reportRepo.deteleDetailReport(detailReport);
    }
}
