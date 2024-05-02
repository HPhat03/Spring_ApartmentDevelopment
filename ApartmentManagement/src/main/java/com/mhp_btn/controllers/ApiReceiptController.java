package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.services.ReceiptService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiReceiptController {
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private RentalConstractService constractService;

    @GetMapping(path = "/receipt/", produces = "application/json")
    public ResponseEntity<List<ApartmentReceipt>> list() {
        List<ApartmentReceipt> receipts = this.receiptService.getAllReceipt();
        if (receipts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @DeleteMapping(path = "/receipt/{id}")
    public ResponseEntity<?> deleteFloorById(@PathVariable int id) {
        ApartmentReceipt receipt = receiptService.getReceiptById(id);
        if (receipt == null) {
            return new ResponseEntity<>("Can not find receipt with" + id, HttpStatus.NOT_FOUND);
        }

        receiptService.deleteReiptById(id);
        return new ResponseEntity<>("Delete success with receipt is ID " + id, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/receipt/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addReceipt(@RequestBody Map<String, String> params) {
        if (params.containsKey("month") && params.containsKey("createdDate") && params.containsKey("total")) {
            int month = Integer.parseInt(params.get("month"));
            double total = Double.parseDouble(params.get("total"));
            int apartmentId = Integer.parseInt(params.get("apartmentId"));
            Date createdDate;
            try {
                createdDate = StringUtil.dateFormater().parse(params.get("createdDate"));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            ApartmentReceipt receipt = new ApartmentReceipt();
            receipt.setMonth(month);
            receipt.setCreatedDate(createdDate);
            receipt.setTotal((int) total);

            ApartmentRentalConstract apartmentRental = constractService.getRentalConstractById(apartmentId);
            if(apartmentRental != null){
                receipt.setApartmentId(apartmentRental);
            }
            else{
                return new ResponseEntity<>("Can not find apartment id : " +apartmentId, HttpStatus.NOT_FOUND);
            }

            //save
            receiptService.addReceipt(receipt);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new IllegalArgumentException("missing required information");
        }
    }

    @PatchMapping(value = "/receipt/{id}", produces = "application/json")
    public ResponseEntity<ApartmentReceipt> updateReceiptById(@PathVariable int id, @RequestBody Map<String, String> updates) {
        ApartmentReceipt receipt = receiptService.getReceiptById(id);

        if (receipt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Cập nhật thông tin hóa đơn nếu có
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "month":
                    receipt.setMonth(Integer.parseInt(value));
                    break;
                case "createdDate":
                    try {
                        Date createdDate = StringUtil.dateFormater().parse(value);
                        receipt.setCreatedDate(createdDate);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Định dạng ngày tháng không hợp lệ: " + value);
                    }
                    break;
                case "total":
                    receipt.setTotal(Integer.parseInt(value));
                    break;
                case "apartmentId":
                    ApartmentRentalConstract constract = constractService.getConstractById(Integer.parseInt(value));
                    if (constract != null) {
                        receipt.setApartmentId(constract);
                    } else {
                        throw new IllegalArgumentException("Apartment with ID " + value + " not found");
                    }
                    break;
                default:
                    // Nếu trường cập nhật không hợp lệ, bạn có thể xử lý tùy ý
                    throw new IllegalArgumentException("Trường cập nhật không hợp lệ: " + key);
            }
        }

        receiptService.updateReceipt(receipt);

        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }



}


