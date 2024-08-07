package com.mhp_btn.controllers;


import com.mhp_btn.pojo.ApartmentRelativeRegistry;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.services.RelativeRegistryService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.UserService;
import com.mhp_btn.utils.StringUtil;
import java.security.Principal;
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
public class ApiRelativeRegistryController {
    @Autowired
    private RelativeRegistryService registryService;
    @Autowired
    private RentalConstractService apartmentService;
    @Autowired
    private UserService userService;

    @GetMapping(path = "/relative_registries/", produces = "application/json")
    public ResponseEntity<List<ApartmentRelativeRegistry>> list() {
        List<ApartmentRelativeRegistry> relativeRegistries = this.registryService.getAllRelativeRegistry();
        if( relativeRegistries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(relativeRegistries, HttpStatus.OK);
    }

    @DeleteMapping(path = "/relative_registries/{id}")
    public ResponseEntity<?> deleteRelativeRegistryById(@PathVariable int id) {
        ApartmentRelativeRegistry relativeRegistry = registryService.getRelativeRegistryById(id);
        if (relativeRegistry == null) {
            return new ResponseEntity<>("Can not find relative registry with" + id, HttpStatus.NOT_FOUND);
        }

        registryService.deleteReceiptById(id);
        return new ResponseEntity<>("Delete success with relative registry is ID " + id, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/relative_registries/")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<?> addRelativeRegistry(@RequestBody Map<String, String> params, Principal p) {
        if (params.containsKey("name") && params.containsKey("startDate")
                && params.containsKey("endDate")) {

            String name = params.get("name");
            
            int apartmentId = Integer.parseInt(params.get("apartmentId"));
            Date startDate;
            Date endDate;
            try {
                startDate = StringUtil.dateFormater().parse(params.get("startDate"));
                endDate = StringUtil.dateFormater().parse(params.get("endDate"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            // Kiểm tra nếu ngày bắt đầu lớn hơn ngày kết thúc
            if (startDate.after(endDate)) {
                return new ResponseEntity<>("Ngày bắt đầu không thể trước ngày kết thúc", HttpStatus.OK);
            }
            if (startDate.before(new Date())){
                return new ResponseEntity<>("Không thể đăng kí trước thời điểm hiện tại", HttpStatus.OK);
            }
            ApartmentRentalConstract apartmentRental = apartmentService.getRentalConstractById(apartmentId);
            if(apartmentRental==null)
                return new ResponseEntity<>("Không tìm thấy chung cư với mã: " +apartmentId, HttpStatus.OK);
            if(!apartmentService.checkRenter(apartmentId, p.getName()))
                return new ResponseEntity<>("Không thể đăng kí cho chung cư khác", HttpStatus.OK);
            
            ApartmentRelativeRegistry apartmentRelativeRegistry = new ApartmentRelativeRegistry();
            apartmentRelativeRegistry.setActive((short) 1);
            apartmentRelativeRegistry.setName(name);
            apartmentRelativeRegistry.setStartDate(startDate);
            apartmentRelativeRegistry.setEndDate(endDate);
            apartmentRelativeRegistry.setApartmentId(apartmentRental);
         

            //save
            registryService.addRelativeRegistry(apartmentRelativeRegistry);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new IllegalArgumentException("missing required information");
        }
    }

    @PatchMapping(value = "/relative_registries/{id}", produces = "application/json")
    public ResponseEntity<ApartmentRelativeRegistry> updateRelativeRegistryById(@PathVariable int id, @RequestBody Map<String, String> updates) {
        ApartmentRelativeRegistry relativeRegistry = registryService.getRelativeRegistryById(id);

        if (relativeRegistry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "name":
                    relativeRegistry.setName(value);
                    break;
                case "apartmentId":
                    ApartmentRentalConstract constract = apartmentService.getConstractById(Integer.parseInt(value));
                    if (constract != null) {
                        relativeRegistry.setApartmentId(constract);
                    } else {
                        throw new IllegalArgumentException("Apartment with ID " + value + " not found");
                    }
                    break;
                case "startDate":
                    try {
                        Date startDate = StringUtil.dateFormater().parse(value);
                        if (relativeRegistry.getEndDate() != null && startDate.after(relativeRegistry.getEndDate())) {
                            throw new IllegalArgumentException("Start date cannot be after end date");
                        }
                        relativeRegistry.setStartDate(startDate);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid start date format");
                    }
                    break;

                case "endDate":
                    try {
                        Date endDate = StringUtil.dateFormater().parse(value);
                        if (relativeRegistry.getStartDate() != null && endDate.before(relativeRegistry.getStartDate())) {
                            throw new IllegalArgumentException("End date cannot be before start date");
                        }
                        relativeRegistry.setEndDate(endDate);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid end date format");
                    }
                    break;
                case "isActive":
                    relativeRegistry.setActive(Short.parseShort(value));
                    break;
                default:
                    throw new IllegalArgumentException("Trường cập nhật không hợp lệ: " + key);
            }
        }

        registryService.updateRelativeRegistry(relativeRegistry);

        return new ResponseEntity<>(relativeRegistry, HttpStatus.OK);
    }
}
