package com.mhp_btn.controllers;


import com.mhp_btn.pojo.*;
import com.mhp_btn.services.DetailReportService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ReportService;
import com.mhp_btn.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private RentalConstractService apartmentService;
    @Autowired
    private DetailReportService detailService;

    @GetMapping(path="/apartment/{apartmentId}/reports", produces = "application/json")
    public ResponseEntity<?> getAllReportByApartmentId(@PathVariable int apartmentId) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        List<ApartmentReport> reports = reportService.getAllReportByApartmentId(apartmentId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @DeleteMapping(path = "/apartment/{apartmentId}/reports/{reportId}")
    public ResponseEntity<?> deleteReportByApartmentId(@PathVariable("apartmentId") int apartmentId,
                                                       @PathVariable("reportId") int reportId) {
        // Kiểm tra xem căn hộ có tồn tại không
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        // Kiểm tra xem báo cáo có tồn tại trong căn hộ không
        List<ApartmentReport> reports = reportService.getAllReportByApartmentId(apartmentId);
        boolean reportExists = false;
        for (ApartmentReport report : reports) {
            if (report.getId() == reportId) {
                reportExists = true;
                break;
            }
        }
        if (!reportExists) {
            return new ResponseEntity<>("Report not found with ID: " + reportId + " in Apartment with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        // Thực hiện xóa báo cáo
        reportService.deleteReportById(reportId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/apartment/{apartmentId}/reports")
    public ResponseEntity<?> createReportByApartmentId(@PathVariable("apartmentId") int apartmentId,
                                                       @RequestBody Map<String, String> params) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        if (params.get("title") == null || params.get("createdDate") == null || params.get("content") == null) {
            return new ResponseEntity<>("missing required information.", HttpStatus.BAD_REQUEST);
        }

        Date createdDate;
        try {
            createdDate = StringUtil.dateFormater().parse(params.get("createdDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ApartmentReport newReport = new ApartmentReport();
        newReport.setApartmentId(apartment);
        newReport.setCreatedDate(createdDate);
        newReport.setTitle(params.get("title"));
        reportService.addReport(newReport);

        // Tạo báo cáo chi tiết
        ApartmentDetailReport newDetail = new ApartmentDetailReport();
        newDetail.setReportId(newReport);
        newDetail.setContent(params.get("content"));
        detailService.addDetailReport(newDetail);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping(value="/apartment/{apartmentId}/reports/{reportId}" , produces = "application/json")
    public ResponseEntity<?> updateReportByReportId(@PathVariable("apartmentId") int apartmentId,
                                                    @PathVariable("reportId") int reportId,
                                                    @RequestBody Map<String, Object> updateFields) {
        // Kiểm tra xem căn hộ có tồn tại không
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }

        // Kiểm tra xem báo cáo có tồn tại trong căn hộ không
        ApartmentReport existingReport = reportService.getReportById(reportId);
        if (existingReport == null) {
            return new ResponseEntity<>("Report not found with ID: " + reportId + " in Apartment with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }

        // Cập nhật thông tin của báo cáo từ dữ liệu được gửi lên
        if (updateFields.containsKey("title")) {
            existingReport.setTitle((String) updateFields.get("title"));
        }

        if (updateFields.containsKey("createdDate")) {
            try {
                Date createdDate = StringUtil.dateFormater().parse((String) updateFields.get("createdDate"));
                existingReport.setCreatedDate(createdDate);
            } catch (ParseException e) {
                return new ResponseEntity<>("Invalid createdDate format", HttpStatus.BAD_REQUEST);
            }
        }
        existingReport.setApartmentId(apartment);
        reportService.updateReport(existingReport);

        return new ResponseEntity<>(existingReport, HttpStatus.OK);
    }


}
