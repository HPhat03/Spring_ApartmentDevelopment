package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/survey_request")
@PropertySource("classpath:configs.properties")
public class SurveyRequestController {
    @Autowired
    private SurveyRequestService requestService;
    @Autowired
    private UserService userService;
    @Autowired
    private DetailRequestService detailRequestService;
    @Autowired
    private SurveyResponseService surveyResponseService;
    @Autowired
    private DetailResponseService detailResponseService;
    @Autowired
    private Environment env;
    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("survey.pagesize"));

        long totalItems = this.requestService.countSurvey();
        System.out.println(totalItems);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Lấy trang hiện tại từ tham số request, mặc định là trang 1
        int currentPage = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        System.out.println(totalPages);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("requests", this.requestService.getAllSurveyRequest(params));

        return "surveyRequest";
    }
    @GetMapping("/{id}")
    public String detail(@PathVariable int id,Model model) {
        model.addAttribute("requests", this.requestService.getSurveyRequestById(id));
        model.addAttribute("detail_requests", this.detailRequestService.getAllDetailRequestByRequestID(id));

        model.addAttribute("responses",this.surveyResponseService.getAllBySurveyId(id));

        return "detailRequest";
    }
    @GetMapping("/add")
    public String addSurvey(Model model) {
//        model.addAttribute("surveyRequest", new ApartmentSurveyRequest());
//        model.addAttribute("detailRequest", new ApartmentDetailRequest());
        return "addRequest";
    }
    @GetMapping("/{idRequest}/response/{id}")
    public String detailResponse(@PathVariable int id,@PathVariable int idRequest,Model model) {
        model.addAttribute("requests", this.requestService.getSurveyRequestById(idRequest));
        model.addAttribute("detail_requests", this.detailRequestService.getAllDetailRequestByRequestID(idRequest));

        model.addAttribute("responses",this.surveyResponseService.getSurveyResponseById(idRequest));
        model.addAttribute("detail_responses", this.detailResponseService.getAllDetailResponseByResponseID(id));
        return "detailResponse";
    }


}
