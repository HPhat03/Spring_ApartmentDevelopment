/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatRepository {
    List<Object[]> statSurveybyId(int id);
    List<Object[]> statRevenue(int year, String filter);
}
