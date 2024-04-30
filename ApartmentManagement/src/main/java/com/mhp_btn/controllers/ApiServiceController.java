/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.services.ServiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiServiceController {
    
    @Autowired
    private ServiceService ss;
    
    @GetMapping(path = "/service/")
    public ResponseEntity<List<ApartmentService>> list(){
        return new ResponseEntity<>(this.ss.getServices(), HttpStatus.OK);
    }
}
