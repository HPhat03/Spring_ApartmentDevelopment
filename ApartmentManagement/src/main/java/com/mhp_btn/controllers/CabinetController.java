package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.SmartCabinetService;
import com.mhp_btn.utils.TwilioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
@Controller
@RequestMapping("/admin/cabinets")
@PropertySource("classpath:configs.properties")
public class CabinetController {

    @Autowired
    private SmartCabinetService scS;

    @Autowired
    private RentalConstractService rcs;
    @Autowired
    private Environment env;


    @GetMapping("/")
    public String index(Model model, @RequestParam HashMap<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("room.pagesize"));

        long total = this.scS.countCabinets();
        int totalPages = (int) Math.ceil((double) total / pageSize);
        int currentPage = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("cabinets", this.scS.getAllSmartCabinets(params));
        return "cabinets";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable int id, @RequestParam HashMap<String, String> params) {
        model.addAttribute("cabinet", this.scS.getSmartCabinetById(id));
        ApartmentSmartCabinet c = this.scS.getSmartCabinetById(id);
        ApartmentRentalConstract constract = c.getApartmentId();
        model.addAttribute("contract", constract);
        return "addCabinet";
    }

    @GetMapping("/add")
    public String addView(Model model, @RequestParam HashMap<String, String> params) {
        model.addAttribute("cabinet", new ApartmentSmartCabinet());
        model.addAttribute("contracts", this.rcs.getAllRentalConstract(params));
        
        return "addCabinet";
    }

    @PostMapping(path = "/add")
    public String addCabinet(@ModelAttribute("cabinet")@Valid ApartmentSmartCabinet cabinet, BindingResult rs) {
        System.out.println(cabinet.getDecription() + " " + cabinet.getId());
        if (!rs.hasErrors()) {
            if (cabinet.getId() == null) {
                cabinet.setUpdatedDate(new Date());
                cabinet.setCreatedDate(new Date());
                this.scS.addCabinet(cabinet);
            } else {
                cabinet.setUpdatedDate(new Date());
                this.scS.updateCabinet(cabinet);
            }
            System.out.println(cabinet.getApartmentId());
            TwilioUtil.SendSMS("+84365051699", String.format("\n[PN APARTMENT THÔNG BÁO]\nQuý khách phòng %s có một đơn hàng vừa được cập nhật tại tủ điện tử.\nVào ngày %s.\nThông tin chi tiết xem tại: www.pnapartment.com",
                    rcs.getConstractById(cabinet.getApartmentId().getId()).getRoomId().getRoomNumber() , cabinet.getUpdatedDate().toString()));


            return "redirect:/admin/cabinets/";
        }
        return "addCabinet";
        
    }


}
