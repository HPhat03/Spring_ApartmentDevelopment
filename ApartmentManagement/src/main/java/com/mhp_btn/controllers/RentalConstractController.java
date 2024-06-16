package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.services.OtherMemberService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ServiceConstractService;
import com.mhp_btn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/constracts")
public class RentalConstractController {
    @Autowired
    private RentalConstractService rcs;
    @Autowired
    private UserService us;
    @Autowired
    private ServiceConstractService scs;
    @Autowired
    private OtherMemberService otherService;

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

    @GetMapping("/add")
    public String addConstract(Model model) {
        model.addAttribute("constract", new ApartmentRentalConstract());
        return "addConstract";
    }
    @GetMapping("/{id}")
    public String detailConstract(Model model, @PathVariable int id) {

        ApartmentRentalConstract con = this.rcs.getConstractById(id);
        ApartmentResident resident = con.getResidentId();
        ApartmentUser customer = this.us.getUserByID(resident.getUserId());
        String nameCustomer = customer.getName();
        con.setCustomerName(nameCustomer);

        model.addAttribute("constract", con);

        List<ApartmentService> listServices = this.scs.getServicesByApartmentId(id);
        model.addAttribute("constractServices",listServices);
        return "detailConstract";
    }

    @GetMapping("/edit/{id}")
    public String editConstract(Model model, @PathVariable int id) {

        ApartmentRentalConstract con = this.rcs.getConstractById(id);
        ApartmentResident resident = con.getResidentId();
        ApartmentUser customer = this.us.getUserByID(resident.getUserId());
        String nameCustomer = customer.getName();
        con.setCustomerName(nameCustomer);

        model.addAttribute("constract", con);

        // lấy các dịch vụ theo id aparment
        List<ApartmentService> listServices = this.scs.getServicesByApartmentId(id);
        model.addAttribute("constractServices",listServices);

        // lấy other member theo id apartment
        List<ApartmentOtherMember> listMember = this.otherService.getOtherMembersByApartmentId(id);
        model.addAttribute("memberConstract", listMember);
        return "editConstract";
    }

}
