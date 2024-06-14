package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ResidentService;
import com.mhp_btn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/constracts")
public class RentalConstractController {
    @Autowired
    private RentalConstractService rcs;
    @Autowired
    private ResidentService rsl;
    @Autowired
    private UserService us;

    @GetMapping("/")
    public String index(Model model, @RequestParam HashMap<String, String> params) {

        List<ApartmentRentalConstract> constracts = this.rcs.getAllRentalConstract(params);
        for (ApartmentRentalConstract con : constracts) {
            ApartmentResident resident = con.getResidentId();
            ApartmentUser customer = this.us.getUserByID(resident.getUserId());
            String nameCustomer = customer.getName();
            con.setCustomerName(nameCustomer);
        }
        model.addAttribute("constracts", constracts);
        return "rentalConstract";
    }
}
