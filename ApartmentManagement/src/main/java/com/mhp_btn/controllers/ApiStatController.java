/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.mhp_btn.repositories.StatRepository;
import com.mhp_btn.services.StatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/api")
public class ApiStatController {
    @Autowired
    private StatService statService;
    
    @GetMapping(path = "/survey/{id}/stat/", produces = "application/json")
    public ResponseEntity<List<Object[]>> StatSurvey(@PathVariable int id){
        List<Object[]> data = statService.statSurvey(id);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
