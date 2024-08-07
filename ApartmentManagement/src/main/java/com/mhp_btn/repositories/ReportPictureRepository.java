/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentReportPicture;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface ReportPictureRepository {
    void addOrUpdateReportPicture(ApartmentReportPicture p);

    List<ApartmentReportPicture> getPicturesByReportId(int reportId) ;
}
