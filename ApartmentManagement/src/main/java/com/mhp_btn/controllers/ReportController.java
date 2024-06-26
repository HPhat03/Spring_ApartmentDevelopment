package com.mhp_btn.controllers;


import com.mhp_btn.services.DetailReportService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ReportPictureService;
import com.mhp_btn.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequestMapping("/admin/reports")
@PropertySource("classpath:configs.properties")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private DetailReportService detailReportService;
    @Autowired
    private ReportPictureService reportPictureService;
    @Autowired
    private Environment env;
    @GetMapping("/")
    public String index(Model model,@RequestParam Map<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("room.pagesize"));

        long total = this.reportService.countReport();
        int totalPages = (int) Math.ceil((double) total / pageSize);
        int currentPage = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("reports", this.reportService.getAllReport(params));
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
