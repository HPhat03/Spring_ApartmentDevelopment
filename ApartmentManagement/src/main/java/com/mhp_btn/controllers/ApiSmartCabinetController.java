package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ReportService;
import com.mhp_btn.services.SmartCabinetService;
import com.mhp_btn.utils.StringUtil;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiSmartCabinetController {
    @Autowired
    private SmartCabinetService cabinetService;
    @Autowired
    private RentalConstractService apartmentService;

    // Lay tat ca danh sach cac tu
    @GetMapping(path = "/smart_cabinets", produces = "application/json")
    public ResponseEntity<List<ApartmentSmartCabinet>> getAllSmartCabinet() {
        List<ApartmentSmartCabinet> smartCabinets = cabinetService.getAllSmartCabinets();
        return new ResponseEntity<>(smartCabinets, HttpStatus.OK);
    }

    // lay tu do thong minh theo id apartment
    @GetMapping(path="/apartment/{apartmentId}/smart_cabinets", produces = "application/json")
    public ResponseEntity<?> getAllCabinetsByApartmentId(@PathVariable int apartmentId) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        List<ApartmentSmartCabinet> cabinets = cabinetService.getAllSmartCabinetByApartmentId(apartmentId);
        return new ResponseEntity<>(cabinets, HttpStatus.OK);
    }
    // Lấy thông tin một tủ thông minh cụ thể
    @GetMapping(path="/apartment/{apartmentId}/smart_cabinets/{cabinetId}", produces = "application/json")
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

    @DeleteMapping(path = "/smart_cabinets/{cabinetId}")
    public ResponseEntity<?> deleteCabinetById(@PathVariable("cabinetId") int cabinetId) {
        ApartmentSmartCabinet cabinet = cabinetService.getSmartCabinetById(cabinetId);
        if(cabinet == null)
        {
            return new ResponseEntity<>("Smart cabinet not found with ID: " + cabinetId, HttpStatus.NOT_FOUND);
        }

        cabinetService.deleteCabinetById(cabinetId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/smart_cabinets/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addSmartCabinet(@RequestBody Map<String, String> params) {

            String desc = params.get("description");
            String status = params.get("status");
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
            return ResponseEntity.status(HttpStatus.CREATED).build();

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
