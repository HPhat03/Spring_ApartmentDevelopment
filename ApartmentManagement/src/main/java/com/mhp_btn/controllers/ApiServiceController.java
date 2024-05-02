/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.services.ServiceService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiServiceController {

    @Autowired
    private ServiceService ss;

    @GetMapping(path = "/service/")
    public ResponseEntity<List<ApartmentService>> list() {
        return new ResponseEntity<>(this.ss.getServices(), HttpStatus.OK);
    }

    @PostMapping(path = "/service/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addService(@RequestBody ApartmentService request) {

        String name = request.getName();
        int price = request.getPrice();
        short isActive = 1;

        ApartmentService newService = new ApartmentService();
        newService.setName(name);
        newService.setPrice(price);
        newService.setIsActive(isActive);

        ss.addService(newService);
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<String> deleteServiceById(@PathVariable int id) {

        ApartmentService service = ss.getServiceById(id);
        if (service == null) {
            return new ResponseEntity<>("Không tìm thấy dịch vụ với ID " + id, HttpStatus.NOT_FOUND);
        }


        ss.deleteServiceById(id);
        return new ResponseEntity<>("Đã xóa phòng có ID " + id, HttpStatus.OK);
    }

    @PatchMapping(value = "/service/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApartmentService> updateServiceById(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        ApartmentService service = ss.getServiceById(id);
        if (service == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy dịch vụ với ID " + id);
        }
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case "name":
                    service.setName((String) value);
                    break;
                case "price":
                   service.setPrice((int) value);
                    break;
                case "is_active":
                    service.setIsActive((short) value);
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trường không hợp lệ: " + key);
            }
        }

        ss.updateervice(service);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }




}
