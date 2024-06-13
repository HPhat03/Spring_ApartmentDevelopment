/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.repositories.StatRepository;
import com.mhp_btn.services.StatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StatServiceImpl implements StatService{
    
    @Autowired
    private StatRepository repo;
    @Override
    public List<Object[]> statSurvey(int id) {
        return repo.statSurveybyId(id);
    }



    @Override
    public List<Object[]> statRevenue(int year, String filter) {
        return this.repo.statRevenue(year,filter);
    }

}
