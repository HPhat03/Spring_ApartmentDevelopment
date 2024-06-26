package com.mhp_btn.controllers;


import com.mhp_btn.services.DetailReportService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ReportPictureService;
import com.mhp_btn.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private DetailReportService detailReportService;
    @Autowired
    private ReportPictureService reportPictureService;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("reports", this.reportService.getAllReport());
        return "reports";
    }
    @GetMapping("/{id}")
    public String detailReport(Model model,@PathVariable int id) {
        model.addAttribute("report", this.reportService.getReportById(id));
        model.addAttribute("details", this.detailReportService.getDetailReportByReportId(id));
        System.out.println(this.reportPictureService.getPicturesByReportId(id));
        model.addAttribute("pictures", this.reportPictureService.getPicturesByReportId(id));
        return "detailReport";
    }
}
