package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.SmartCabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
@Controller
@RequestMapping("/cabinets")
public class CabinetController {

    @Autowired
    private SmartCabinetService scS;

    @Autowired
    private RentalConstractService rcs;

    @GetMapping("/")
    public String index(Model model, @RequestParam HashMap<String, String> params) {
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
    public String addCabinet(@Valid @ModelAttribute("cabinet") ApartmentSmartCabinet cabinet, BindingResult result) {

        try {
            if (cabinet.getId() == null) {
                cabinet.setUpdatedDate(new Date());
                cabinet.setCreatedDate(new Date());
                this.scS.addOrUpdate(cabinet);
            } else {

                cabinet.setUpdatedDate(new Date());
                this.scS.updateCabinet(cabinet);
            }
            return "redirect:/cabinets/";
        } catch (Exception ex) {
            System.err.println("Lỗi khi thêm/cập nhật cabinet: " + ex.getMessage());
        }
        return "addCabinet";
    }

//    @PostMapping("/{id}")
//    public String editCabinet(@ModelAttribute("cabinet") ApartmentSmartCabinet cabinet, @PathVariable int id) {
//        if (cabinet.getId() == null) {
//            cabinet.setId(id);
//        }
//        this.scS.updateCabinet(cabinet);
//        return "redirect:/cabinets/";
//    }
}
