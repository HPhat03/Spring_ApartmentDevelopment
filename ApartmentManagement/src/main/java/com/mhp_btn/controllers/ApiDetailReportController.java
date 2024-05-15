package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailReport;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentReport;
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
public class ApiDetailReportController {
    @Autowired
    private DetailReportService detailService;
    @Autowired
    private RentalConstractService apartmentService;
    @Autowired
    private ReportService reportService;

    //lay chi tiet bao cao theo id
    @GetMapping(path="/apartment/{apartmentId}/reports/{reportId}", produces = "application/json")
    public ResponseEntity<?> getDetailReportByReportId(@PathVariable int apartmentId,
                                                            @PathVariable int reportId) {
        // kiem tra su ton tai cua id apartment
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        //kiem tra su ton tai id report
        ApartmentReport report= reportService.getReportById(reportId);
        if(report == null){
            return new ResponseEntity<>("Report not found with ID: " + reportId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailReport detail = detailService.getDetailReportByReportId(reportId);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
    @PostMapping("/apartment/{apartmentId}/reports/{reportId}")
    public ResponseEntity<?> createReportByApartmentId(@PathVariable("apartmentId") int apartmentId,
                                                       @PathVariable("reportId") int reportId,
                                                       @RequestBody Map<String, String> params) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        ApartmentReport report= reportService.getReportById(reportId);
        if(report == null){
            return new ResponseEntity<>("Report not found with ID: " + reportId, HttpStatus.NOT_FOUND);
        }
        if (params.get("content") == null ) {
            return new ResponseEntity<>("missing required information.", HttpStatus.BAD_REQUEST);
        }


        ApartmentDetailReport newDetail = new ApartmentDetailReport();
        newDetail.setReportId(report);
        newDetail.setContent(params.get("content"));
        detailService.addDetailReport(newDetail);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping(value="/apartment/{apartmentId}/reports/{reportId}/details" , produces = "application/json")
    public ResponseEntity<?> updateReportByReportId(@PathVariable("apartmentId") int apartmentId,
                                                    @PathVariable("reportId") int reportId,
                                                    @RequestBody Map<String, Object> updateFields) {
        // Kiểm tra xem căn hộ có tồn tại không
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        ApartmentReport report= reportService.getReportById(reportId);
        if(report == null){
            return new ResponseEntity<>("Report not found with ID: " + reportId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailReport detailReport = detailService.getDetailReportByReportId(reportId);
        if(detailReport == null){
            return new ResponseEntity<>("Report detail not found ", HttpStatus.NOT_FOUND);
        }
        //detailReport.getId();
        // Cập nhật thông tin của báo cáo từ dữ liệu được gửi lên
        // Cập nhật thông tin của báo cáo từ dữ liệu được gửi lên
        if (updateFields.containsKey("content")) {
            detailReport.setContent((String) updateFields.get("content"));
        }


        detailService.updateDetailReport(detailReport);

        return new ResponseEntity<>(detailReport, HttpStatus.OK);
    }

    @DeleteMapping(path = "/apartment/{apartmentId}/reports/{reportId}/details")
    public ResponseEntity<?> deleteReportByApartmentId(@PathVariable("apartmentId") int apartmentId,
                                                       @PathVariable("reportId") int reportId) {
        // Kiểm tra xem căn hộ có tồn tại không
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        ApartmentReport report= reportService.getReportById(reportId);
        if(report == null){
            return new ResponseEntity<>("Report not found with ID: " + reportId, HttpStatus.NOT_FOUND);
        }
        ApartmentDetailReport detailReport = detailService.getDetailReportByReportId(reportId);
        if(detailReport == null){
            return new ResponseEntity<>("Report detail not found ", HttpStatus.NOT_FOUND);
        }
        // Thực hiện xóa báo cáo
        detailService.deteleDetailReport(detailReport);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
