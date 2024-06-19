package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.SmartCabinetService;
import com.mhp_btn.utils.TwilioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api")
public class ApiSmartCabinetController {
    @Autowired
    private SmartCabinetService cabinetService;
    @Autowired
    private RentalConstractService apartmentService;

    // Lay tat ca danh sach cac tu
//    @GetMapping(path = "/api/smart_cabinets", produces = "application/json")
//    public ResponseEntity<List<ApartmentSmartCabinet>> getAllSmartCabinet() {
//        List<ApartmentSmartCabinet> smartCabinets = cabinetService.getAllSmartCabinets();
//        return new ResponseEntity<>(smartCabinets, HttpStatus.OK);
//    }

    // lay tu do thong minh theo id apartment
    @GetMapping(path="/api/apartment/{apartmentId}/smart_cabinets", produces = "application/json")
    public ResponseEntity<?> getAllCabinetsByApartmentId(@PathVariable int apartmentId) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        List<ApartmentSmartCabinet> cabinets = cabinetService.getAllSmartCabinetByApartmentId(apartmentId);
        return new ResponseEntity<>(cabinets, HttpStatus.OK);
    }
    // Lấy thông tin một tủ thông minh cụ thể
    @GetMapping(path="/api/apartment/{apartmentId}/smart_cabinets/{cabinetId}", produces = "application/json")
    public ResponseEntity<?> getSmartCabinetById(@PathVariable int apartmentId, @PathVariable int cabinetId) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        ApartmentSmartCabinet cabinet = cabinetService.getSmartCabinetById(cabinetId);
        if(cabinet == null)
        {
            return new ResponseEntity<>("Smart cabinet not found with ID: " + cabinetId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cabinet, HttpStatus.OK);
    }

    @DeleteMapping(path = "/cabinets/{cabinetId}")
    public ResponseEntity<?> deleteCabinetById(@PathVariable("cabinetId") int cabinetId) {
        ApartmentSmartCabinet cabinet = cabinetService.getSmartCabinetById(cabinetId);
        if(cabinet == null)
        {
            return new ResponseEntity<>("Smart cabinet not found with ID: " + cabinetId, HttpStatus.NOT_FOUND);
        }

        cabinetService.deleteCabinetById(cabinetId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/smart_cabinets/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addSmartCabinet(@RequestBody Map<String, String> params) {

            String desc = params.get("description");
            String status = ApartmentSmartCabinet.Status.PENDING.toString();
            int apartmentId = Integer.parseInt(params.get("apartmentId"));
            Date createdDate = new Date();
            Date updatedDate = new Date();

            ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
            if (apartment == null) {
                return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
            }

            ApartmentSmartCabinet newCabinet = new ApartmentSmartCabinet();
            newCabinet.setApartmentId(apartment);
            newCabinet.setDecription(desc);
            newCabinet.setCreatedDate(createdDate);
            newCabinet.setUpdatedDate(updatedDate);
            newCabinet.setStatus(status);

            cabinetService.addCabinet(newCabinet);
            TwilioUtil.SendSMS("+84365051699", String.format("\n[PN APARTMENT THÔNG BÁO]\nQuý khách phòng %s có một đơn hàng vừa được nhận tại tủ điện tử.\n"
                    + "Vào ngày %s.\nThông tin chi tiết xem tại: www.apartmentTest.com", newCabinet.getApartmentId().getRoomId().getRoomNumber(),  createdDate.toString()));

            return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PatchMapping(value = "/smart_cabinets/{id}/receivce", produces = "application/json")
    public ResponseEntity<Object> ConfirmCabinet(@PathVariable int id){
        ApartmentSmartCabinet cab = cabinetService.getSmartCabinetById(id);
        if (cab == null){
            return new ResponseEntity<>("Không tìm thấy tủ đồ với mã " + id, HttpStatus.NOT_FOUND);
        }
        
        if (!cab.getStatus().equals(ApartmentSmartCabinet.Status.PENDING.toString()) )
            return new ResponseEntity<>("Không thể xác nhận nhận hàng các tủ đồ đã xác nhận trước đó ", HttpStatus.BAD_REQUEST);
        cab.setStatus(ApartmentSmartCabinet.Status.RECEIVCED.toString());
        cab.setUpdatedDate(new Date());
        cabinetService.updateCabinet(cab);
        TwilioUtil.SendSMS("+84365051699", String.format("\n[PN APARTMENT THÔNG BÁO]\nĐơn hàng quý khách phòng %s vừa được nhận tại tủ điện tử.\n"
                    + "Vào ngày %s.\nThông tin chi tiết xem tại: www.apartmentTest.com", cab.getApartmentId().getRoomId().getRoomNumber(),  cab.getUpdatedDate().toString()));

        return ResponseEntity.ok(cab);
    }

    @PatchMapping(value = "/smart_cabinets/{id}", produces = "application/json")
    public ResponseEntity<Object> updateCabinetById(@PathVariable int id,
                                                    @RequestBody Map<String, String> updates) {
        ApartmentSmartCabinet cabinet = cabinetService.getSmartCabinetById(id);
        if (cabinet == null) {
            return new ResponseEntity<>("Smart cabinet not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        if (updates.containsKey("description")) {
            String description = updates.get("description");
            cabinet.setDecription(description);
        }
        if (updates.containsKey("status")) {
            String status = updates.get("status");
            cabinet.setStatus(status);
        }
        cabinet.setUpdatedDate(new Date());

        cabinetService.updateCabinet(cabinet);
        return ResponseEntity.ok(cabinet);
    }

}
