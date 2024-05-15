package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ServiceConstractService;
import com.mhp_btn.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiServiceConstractController {
    @Autowired
    private ServiceConstractService serviceConstractService;
    @Autowired
    private RentalConstractService apartmentService;
    @Autowired
    private ServiceService serviceService;
    @GetMapping(path = "/service_constract", produces = "application/json")
    public ResponseEntity<List<ApartmentServiceConstract>> getAllServiceConstract() {
        List<ApartmentServiceConstract> smartCabinets = serviceConstractService.getAllServiceConstract();
        return new ResponseEntity<>(smartCabinets, HttpStatus.OK);
    }
    @GetMapping(path = "/service_constract/{id}", produces = "application/json")
    public ResponseEntity<ApartmentServiceConstract> getDetailConstractById(@PathVariable int id) {
        ApartmentServiceConstract constract = serviceConstractService.getServiceConstractById(id);
        if (constract != null) {
            return new ResponseEntity<>(constract, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/service_constract/{constractId}")
    public ResponseEntity<?> deleteCabinetById(@PathVariable("constractId") int constractId) {
        ApartmentServiceConstract constract = serviceConstractService.getServiceConstractById(constractId);
        if(constract == null)
        {
            return new ResponseEntity<>("service constract not found with ID: " + constractId, HttpStatus.NOT_FOUND);
        }
        serviceConstractService.deleteServiceConstractById(constractId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/service_constract")
    public ResponseEntity<?> addServiceConstract(@RequestBody Map<String, Integer> params) {
        Integer apartmentId = params.get("apartmentId");
        Integer serviceId = params.get("serviceId");

        // Kiểm tra xem apartmentId và serviceId có tồn tại hay không
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if(apartment == null)
        {
            return new ResponseEntity<>("ID apartment not found:"+apartmentId,HttpStatus.NOT_FOUND);
        }
        ApartmentService service = serviceService.getServiceById(serviceId);
        if(service == null)
        {
            return new ResponseEntity<>("ID service not found:"+ serviceId,HttpStatus.NOT_FOUND);
        }
        if (apartment == null || service == null) {
            return ResponseEntity.badRequest().body("Apartment ID or Service ID is invalid");
        }

        ApartmentServiceConstract constract = new ApartmentServiceConstract();
        constract.setApartmentId(apartment);
        constract.setServiceId(service);

        serviceConstractService.addServiceConstract(constract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping(value = "/service_constract/{id}", produces = "application/json")
    public ResponseEntity<Object> updatConstractById(@PathVariable int id,
                                                    @RequestBody Map<String, String> updates) {
        ApartmentServiceConstract serviceConstract = serviceConstractService.getServiceConstractById(id);
        if (serviceConstract == null) {
            return new ResponseEntity<>("Service constract not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
        if (updates.containsKey("apartmentId")) {
            ApartmentRentalConstract apartment = apartmentService.getConstractById(Integer.parseInt(updates.get("apartmentId")));
            if(apartment == null)
            {
                return new ResponseEntity<>("ID apartment not found",HttpStatus.NOT_FOUND);
            }
            serviceConstract.setApartmentId(apartment);
        }
        if (updates.containsKey("serviceId")) {
            ApartmentService service = serviceService.getServiceById(Integer.parseInt(updates.get("serviceId")));
            if(service == null)
            {
                return new ResponseEntity<>("ID service not found:",HttpStatus.NOT_FOUND);
            }
            serviceConstract.setServiceId(service);
        }
        serviceConstractService.updateServiceConstract(serviceConstract);

        return new ResponseEntity<>(serviceConstract, HttpStatus.OK);
    }


}
