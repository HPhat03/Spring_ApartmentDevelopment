/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentReportPicture;
import com.mhp_btn.repositories.ReportPictureRepository;
import com.mhp_btn.services.ReportPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReportPictureServiceImpl implements ReportPictureService{
    
    @Autowired
    private ReportPictureRepository rp;
    @Override
    public void addOrUpdate(ApartmentReportPicture p) {
        rp.addOrUpdateReportPicture(p);
    }
    
}
