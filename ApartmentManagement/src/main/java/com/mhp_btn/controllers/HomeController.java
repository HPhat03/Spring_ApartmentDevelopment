/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.cloudinary.Cloudinary;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.services.implement.ServiceServiceImpl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class HomeController {
    @Autowired
    private ServiceServiceImpl ss;
    @Autowired
    private Cloudinary cloudinary;
    
    
    @RequestMapping("/admin")
    public String Index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("services", this.ss.getService(params));
        return "index";
    }
}
