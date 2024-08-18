package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.services.DetailReceiptService;
import com.mhp_btn.services.PaymentService;
import com.mhp_btn.services.ReceiptService;
import com.mhp_btn.services.RentalConstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;


@Controller
@RequestMapping("/admin/receipts")
@PropertySource("classpath:configs.properties")
public class ReceiptController {
    @Autowired
    private ReceiptService rs;
    @Autowired
    private DetailReceiptService detailRs;
    @Autowired
    private RentalConstractService rCS;
    @Autowired
    private Environment env;
    @Autowired
    private PaymentService ps;

    @GetMapping("/")
    public String index(Model model, @RequestParam HashMap<String, String> params) {
        int pageSize = Integer.parseInt(env.getProperty("room.pagesize"));

        long total = this.rs.countReceipt();
        int totalPages = (int) Math.ceil((double) total / pageSize);
        int currentPage = params.get("page") != null ? Integer.parseInt(params.get("page")) : 1;
        if (params.get("page") == null)
            params.put("page", "1");
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("receipts", this.rs.getAll(params));
        return "receipts";
    }
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable int id) {
        ApartmentReceipt rcp = this.rs.getReceiptById(id);
        model.addAttribute("receipt", rcp);
        model.addAttribute("detail_receipt", this.detailRs.getDetailReceiptsByReceiptId(id));
        model.addAttribute("payment", rcp.getApartmentPaymentSet());
        return "detailReceipt";
    }
    @GetMapping("/add")
    public String addView(Model model, @RequestParam HashMap<String, String> params) {
        model.addAttribute("constracts", this.rCS.getAllRentalConstract(params));
        model.addAttribute("receipt", new ApartmentReceipt());
        model.addAttribute("detail_receipt", new ApartmentDetailReceipt());

        return "addReceipt";
    }
}
