/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentResident;

/**
 *
 * @author Admin
 */
public interface ResidentService {
    void save(ApartmentResident resident);
    void update(ApartmentResident resident);
    ApartmentResident getResidentById(int id) ;
}
