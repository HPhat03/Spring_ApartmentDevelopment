package com.mhp_btn.controllers;

import com.cloudinary.Cloudinary;
import com.mhp_btn.components.CloudinaryUtil;
import com.mhp_btn.pojo.ApartmentDetailReport;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.pojo.ApartmentReportPicture;
import com.mhp_btn.serializers.ReportSerializer;
import com.mhp_btn.services.DetailReportService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ReportPictureService;
import com.mhp_btn.services.ReportService;
import com.mhp_btn.utils.StringUtil;
import java.io.IOException;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.PathParam;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@RequestMapping("/api")
public class ApiReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private RentalConstractService apartmentService;
    @Autowired
    private DetailReportService detailReportService;
    @Autowired
    private ReportPictureService reportPictureService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping(path="/apartment/{apartmentId}/reports", produces = "application/json")
    public ResponseEntity<?> getAllReportByApartmentId(@PathVariable int apartmentId ) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        List<ApartmentReport> reports = reportService.getAllReportByApartmentId(apartmentId, -1);
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
        List<ApartmentReport> reports = reportService.getAllReportByApartmentId(apartmentId, -1);
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

    @PostMapping("/api/reports/")
    @CrossOrigin
    public ResponseEntity<?> createReportByApartmentId(@RequestBody Map<String, Object> params,
                                                       Principal p) {
        
        if(params.get("apartmentId")==null || params.get("title") == null){
            return new ResponseEntity<>("thiếu thông tin", HttpStatus.OK);
        }
        int apartmentId  = (int) params.get("apartmentId");
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Không tìm thầy chung cư với mã: " + apartmentId, HttpStatus.OK);
        }
        if (!apartmentService.checkRenter(apartmentId, p.getName()))
            return new ResponseEntity<>("Không thể báo cáo cho chung cư khác", HttpStatus.OK);

        
        ApartmentReport newReport = new ApartmentReport();
        newReport.setApartmentId(apartment);
        newReport.setCreatedDate(new Date());
        newReport.setTitle((String) params.get("title"));

        reportService.addReport(newReport);
        
        List<Map<String,Object>> details = (List<Map< String, Object>>) params.get("details");
        details.forEach(x -> {
            ApartmentDetailReport temp = new ApartmentDetailReport();
            temp.setContent((String) x.get("content"));
            temp.setReportId(newReport);
            detailReportService.addDetailReport(temp);
            if(x.get("pictures")!=null)
            {
                List<String> pictures = (List<String>) x.get("pictures");
                pictures.forEach(y -> {
                    ApartmentReportPicture pic = new ApartmentReportPicture();
                    pic.setPicture(y);
                    pic.setReportId(temp);
                    reportPictureService.addOrUpdate(pic);
                });
            }
        });
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
    
    @PostMapping(path = "/api/upload/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> uploadImg(@RequestPart MultipartFile[] files) throws IOException {
        if(files.length == 0)
            return new ResponseEntity<>("File trống", HttpStatus.BAD_REQUEST);
        List<String> res = new ArrayList<>();
        for(MultipartFile f : files){
            try{
                res.add(CloudinaryUtil.upload(f, cloudinary));
            }
            catch (IOException e)
            {
                return new ResponseEntity<>(e.toString(), HttpStatus.BAD_GATEWAY);
            }
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
    @GetMapping(path = "/api/reports/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> getReportById(@PathVariable int id, Principal p)
    {
        System.out.println("jeeee");
        ApartmentReport rp = this.reportService.getReportById(id);
        if(rp==null)
            return new ResponseEntity<>("Không tìm thấy", HttpStatus.OK);
        if(!this.apartmentService.checkRenter(rp.getApartmentId().getId(), p.getName()))
            return new ResponseEntity<>("Không thể xem của người khác", HttpStatus.OK);
        else
            return new ResponseEntity<>(ReportSerializer.ReportDetail(rp), HttpStatus.OK);
    }
}
