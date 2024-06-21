package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.repositories.ReportRepository;
import com.mhp_btn.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepo;

    @Override
    public List<ApartmentReport> getAllReportByApartmentId(int id, int page) {
        return this.reportRepo.getAllReportByApartmentId(id, page);
    }

    @Override
    public void deleteReportById(int id) {
        this.reportRepo.deleteReportById(id);
    }

    @Override
    public void addReport(ApartmentReport report) {
        this.reportRepo.addReport(report);
    }

    @Override
    public void updateReport(ApartmentReport report) {
        this.reportRepo.updateReport(report);
    }

    @Override
    public ApartmentReport getReportById(int id) {
        return this.reportRepo.getReportById(id);
    }
}
