package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.serializers.ReceiptSerializer;
import com.mhp_btn.services.DetailReceiptService;
import com.mhp_btn.services.ReceiptService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiReceiptController {
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private RentalConstractService constractService;
    @Autowired
    private DetailReceiptService detailService;


    @GetMapping(path = "/receipt/", produces = "application/json")
    public ResponseEntity<Object> list(@RequestParam HashMap<String, String> params) {
        List<ApartmentReceipt> receipts = this.receiptService.getAllReceipt(params);
        if (receipts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ReceiptSerializer.ReceiptList(receipts), HttpStatus.OK);
    }

    @DeleteMapping(path = "/receipt/{id}")
    public ResponseEntity<?> deleteReceiptById(@PathVariable int id) {
        ApartmentReceipt receipt = receiptService.getReceiptById(id);
        if (receipt == null) {
            return new ResponseEntity<>("Can not find receipt with ID " + id, HttpStatus.NOT_FOUND);
        }

        // Lấy danh sách chi tiết hóa đơn
        List<ApartmentDetailReceipt> detailReceipts = detailService.getDetailReceiptsByReceiptId(id);
        // Xóa từng chi tiết hóa đơn
        for (ApartmentDetailReceipt detailReceipt : detailReceipts) {
            detailService.deleteDetailReceiptById(detailReceipt.getId());
        }

        // Xóa hóa đơn
        receiptService.deleteReiptById(id);

        return new ResponseEntity<>("Delete success with receipt ID " + id + " and its details.", HttpStatus.NO_CONTENT);
    }


    @PostMapping("/receipt/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addReceipt(@RequestBody Map<String, Object> params) {
        if (params.containsKey("month")  && params.containsKey("total")  && params.containsKey("apartmentId")) {
            int month = (int) params.get("month");
            String year = params.containsKey("year") ? (String) params.get("year") : String.valueOf(LocalDate.now().getYear());
            double total = Double.parseDouble((String) params.get("total"));
            int apartmentId = Integer.parseInt((String) params.get("apartmentId"));


            // Tìm ApartmentRentalConstract trước
            ApartmentRentalConstract apartmentRental = constractService.getRentalConstractById(apartmentId);
            if (apartmentRental == null) {
                return new ResponseEntity<>("Không tìm thấy apartment id: " + apartmentId, HttpStatus.NOT_FOUND);
            }

            Date currentDate = new Date();
            ApartmentReceipt receipt = new ApartmentReceipt();
            receipt.setMonth(month);
            receipt.setYear(year);
            receipt.setCreatedDate(currentDate);
            receipt.setTotal((int) total);
            receipt.setApartmentId(apartmentRental);

            // Lưu receipt
            receiptService.addReceipt(receipt);

            // Tạo và lưu ReceiptDetails
            List<Map<String, Object>> detailsList = (List<Map<String, Object>>) params.get("details");
            for (Map<String, Object> detailParams : detailsList) {
                String content = (String) detailParams.get("content");
                double price = Double.parseDouble((String) detailParams.get("price"));

                ApartmentDetailReceipt detail = new ApartmentDetailReceipt();
                detail.setContent(content);
                detail.setPrice(String.valueOf(price));
                detail.setReceiptId(receipt);

                detailService.createDetailByReceiptId(detail);
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new IllegalArgumentException("Thiếu thông tin cần thiết");
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


