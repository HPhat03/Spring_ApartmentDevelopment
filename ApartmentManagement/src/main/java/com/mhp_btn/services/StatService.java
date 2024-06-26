/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatService {
    public List<Object[]> statSurvey(int id);

    public List<Object[]> statsRevenueByPeriod(int year, String period) ;


}
