package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.repositories.DetailReceiptRepository;
import com.mhp_btn.services.DetailReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiDetailReceiptController {
    @Autowired
    private DetailReceiptService detailService;

    @GetMapping(path="/receipt/{receiptId}/details", produces = "application/json")
    public ResponseEntity<List<ApartmentDetailReceipt>> getDetailReceiptsByReceiptId(@PathVariable int receiptId) {
        List<ApartmentDetailReceipt> detailReceipts = detailService.getDetailReceiptsByReceiptId(receiptId);
        return new ResponseEntity<>(detailReceipts, HttpStatus.OK);
    }

    @PostMapping("/receipt/{id}/details")
    public ResponseEntity<?> createDetailReceiptByReceiptId(@PathVariable("id") int id, @RequestBody ApartmentDetailReceipt request) {
        int receiptId = id;
        List<ApartmentDetailReceipt> detail = detailService.getDetailReceiptsByReceiptId(receiptId);

        if(detail.isEmpty()) {
            return new ResponseEntity<>("Receipt not found with ID: " + receiptId, HttpStatus.NOT_FOUND);
        }

        ApartmentDetailReceipt newDetail = new ApartmentDetailReceipt();
        // Lấy đối tượng ApartmentReceipt từ danh sách detail
        ApartmentReceipt apartmentReceipt = detail.get(0).getReceiptId();
        newDetail.setReceiptId(apartmentReceipt);
        newDetail.setContent(request.getContent());
        newDetail.setPrice(request.getPrice());

        detailService.createDetailByReceiptId(newDetail);
        return new ResponseEntity<>("Detail receipt created successfully", HttpStatus.CREATED);
    }


}
