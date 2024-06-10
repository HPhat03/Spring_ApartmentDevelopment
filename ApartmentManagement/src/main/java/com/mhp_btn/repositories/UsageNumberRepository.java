/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentUsageNumber;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UsageNumberRepository {
    void addOrUpdate(ApartmentUsageNumber un);
    List<ApartmentUsageNumber> lastMonthUsage(int ApartmentId, int month, String year);
}
