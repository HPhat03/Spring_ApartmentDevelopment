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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * @author Admin
 */
@Controller
@RequestMapping("/services")
public class ApiServiceController {

    @Autowired
    private ServiceService ss;

    @GetMapping("/")
    public String Index(Model model) {
        model.addAttribute("services", this.ss.getServices());
        return "services"; // Tên này phải khớp với tên định nghĩa trong tiles.xml
    }


    @GetMapping("/add")
    public String addService(Model model) {
        model.addAttribute("service", new ApartmentService());
        return "addService";
    }
    // Chưa bắt looi cac truong nhap lieu
    @PostMapping(path = "/add")
    public String addService(@ModelAttribute("service") @Valid ApartmentService service, BindingResult rs)  {
        // Kiểm tra xem có lỗi không
        if (!rs.hasErrors()) {
            try {
                if (service.getId() == null) {
                    this.ss.addOrUpdate(service);
                } else {
                    // Nếu id không phải null, thực hiện cập nhật
                    this.ss.addOrUpdate(service);
                }
                return "redirect:/services/";
            } catch (Exception ex) {
                System.err.println("Lỗi khi thêm/cập nhật dịch vụ: " + ex.getMessage());
            }
        }
        return "addService";
    }



    @GetMapping("/edit/{id}")
    public String editService(@PathVariable int id, Model model) {
        ApartmentService service = ss.getServiceById(id);
        model.addAttribute("service", service);
        return "addService";
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

//    @PatchMapping(value = "/service/{id}", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<ApartmentService> updateServiceById(@PathVariable int id, @RequestBody Map<String, Object> updates) {
//        ApartmentService service = ss.getServiceById(id);
//        if (service == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy dịch vụ với ID " + id);
//        }
//        for (Map.Entry<String, Object> entry : updates.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            switch (key) {
//                case "name":
//                    service.setName((String) value);
//                    break;
//                case "price":
//                    service.setPrice((int) value);
//                    break;
//                case "is_active":
//                    service.setIsActive((short) value);
//                    break;
//                default:
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trường không hợp lệ: " + key);
//            }
//        }
//
//        ss.updateervice(service);
//        return new ResponseEntity<>(service, HttpStatus.OK);
//    }


}
