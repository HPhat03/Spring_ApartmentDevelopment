/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentUsageNumber;
import com.mhp_btn.repositories.UsageNumberRepository;
import com.mhp_btn.services.UsageNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsageNumberServiceImpl implements UsageNumberService{
    @Autowired
    private UsageNumberRepository repo;
    
    @Override
    public void saveOrUpdate(ApartmentUsageNumber un) {
        this.repo.addOrUpdate(un);
    }

    @Override
    public List<ApartmentUsageNumber> getLastMonthUsage(int apartmentId, int month, String year) {
        return this.repo.lastMonthUsage(apartmentId, month, year);
        }
    
}
