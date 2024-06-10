/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentUsageNumber;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UsageNumberService {
    void saveOrUpdate(ApartmentUsageNumber un);
    List<ApartmentUsageNumber> getLastMonthUsage(int apartmentId, int month, String year);
}
