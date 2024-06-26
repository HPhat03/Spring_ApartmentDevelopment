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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@RequestMapping("/admin/services")
@PropertySource("classpath:configs.properties")
@ControllerAdvice
public class ServicesController {

    @Autowired
    private ServiceService ss;
    @Autowired
    private Environment env;
    @ModelAttribute
    public void commonServices(Model model) {
        model.addAttribute("services", this.ss.getServiceActive());
    }
    @GetMapping("/")
    public String Index(Model model, @RequestParam Map<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("services.pagesize"));

        // Tổng số sản phẩm từ dịch vụ
        List<ApartmentService> totalService = this.ss.getService(params);
        long totalItems = this.ss.countService();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Lấy trang hiện tại từ tham số request, mặc định là trang 1
        int currentPage = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("services", totalService);

        return "services";
    }
    @GetMapping("/add")
    public String addService(Model model) {
        model.addAttribute("service", new ApartmentService());
        return "addService";
    }

    @PostMapping(path = "/add")
    public String addService(@ModelAttribute("service") @Valid ApartmentService service, BindingResult rs)  {
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


}
