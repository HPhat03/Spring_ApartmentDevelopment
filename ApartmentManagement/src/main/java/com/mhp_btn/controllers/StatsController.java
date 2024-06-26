/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhp_btn.repositories.StatRepository;
import com.mhp_btn.services.StatService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.mhp_btn.services.SurveyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Admin
 */
@Controller
@RequestMapping("/admin/stats")
public class StatsController {
    @Autowired
    private StatService statService;
    @Autowired
    private SurveyRequestService sRS;

    //    @GetMapping(path = "/survey/{id}/stat/", produces = "application/json")
//    public ResponseEntity<List<Object[]>> StatSurvey(@PathVariable int id) {
//        List<Object[]> data = statService.statSurvey(id);
//        return new ResponseEntity(data, HttpStatus.OK);
//    }
    @GetMapping("/survey")
    public String surveyStats(@RequestParam("id") int id, Model model) {
        List<Object[]> stats = statService.statSurvey(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStats = mapper.writeValueAsString(stats);
            model.addAttribute("SurveyStatsJson", jsonStats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        model.addAttribute("SurveyStats", stats);
        return "survey_stats";
    }

    @GetMapping("/")
    public String StatsViews(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("surveys", this.sRS.getAllSurveyRequest(params));
        // do du lieu
        String year = params.getOrDefault("year", String.valueOf(LocalDate.now().getYear()));
        String period = params.getOrDefault("period", "MONTH");
        model.addAttribute("RevenueByPeriod", this.statService.statsRevenueByPeriod(Integer.parseInt(year), period));


        return "stats";
    }
}