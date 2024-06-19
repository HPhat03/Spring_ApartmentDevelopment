package com.mhp_btn.controllers;

import com.mhp_btn.pojo.*;
import com.mhp_btn.serializers.ReceiptSerializer;
import com.mhp_btn.services.DetailReceiptService;
import com.mhp_btn.services.ReceiptService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.UsageNumberService;
import com.mhp_btn.utils.StringUtil;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
//@RequestMapping("/api")
public class ApiReceiptController {
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private RentalConstractService constractService;
    @Autowired
    private UsageNumberService usageNumberService;
    @Autowired
    private DetailReceiptService detailReceiptService;

    @GetMapping(path = "/api/receipt/", produces = "application/json")
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
        List<ApartmentDetailReceipt> detailReceipts = detailReceiptService.getDetailReceiptsByReceiptId(id);
        // Xóa từng chi tiết hóa đơn
        for (ApartmentDetailReceipt detailReceipt : detailReceipts) {
            detailReceiptService.deleteDetailReceiptById(detailReceipt.getId());
        }

        // Xóa hóa đơn
        receiptService.deleteReiptById(id);

        return new ResponseEntity<>("Delete success with receipt ID " + id + " and its details.", HttpStatus.NO_CONTENT);
    }


    @PostMapping("/api/receipt/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addReceipt(@RequestBody Map<String, String> params) {
        if (params.containsKey("month") && 
                params.containsKey("year") && 
                params.containsKey("water_usage")  && 
                params.containsKey("electric_usage") && 
                params.containsKey("apartmentId")) {
            int month = Integer.parseInt(params.get("month"));
            String year = params.get("year");
            int apartmentId = Integer.parseInt(params.get("apartmentId"));
            int cur_electric = Integer.parseInt(params.get("electric_usage"));
            int cur_water = Integer.parseInt(params.get("water_usage"));
            
            ApartmentReceipt receipt = new ApartmentReceipt();
            receipt.setMonth(month);
            receipt.setYear(year);
            receipt.setCreatedDate(new Date());
            receipt.setTotal(-1);
            
            ApartmentRentalConstract apartmentRental = constractService.getRentalConstractById(apartmentId);
            if(apartmentRental != null){
                receipt.setApartmentId(apartmentRental);
            }
            else{
                return new ResponseEntity<>("Can not find apartment id : " +apartmentId, HttpStatus.NOT_FOUND);
            }

            //save
            receiptService.addReceipt(receipt);
            int amount_water = 0, amount_electric = 0;
            List<ApartmentUsageNumber> ul = usageNumberService.getLastMonthUsage(apartmentId, month, year);
            for(ApartmentUsageNumber x : ul){
                if(x.getType().equals(ApartmentUsageNumber.Type.ELECTRIC.toString()))
                    amount_electric = cur_electric - x.getNumber();
                if(x.getType().equals(ApartmentUsageNumber.Type.WATER.toString()))
                    amount_water = cur_water - x.getNumber();
            }
            System.out.println(cur_electric + " :: " + amount_electric);
            System.out.println(cur_water + " :: " + amount_water);
            
            int sum = apartmentRental.getFinalPrice();
            String electric = "Tiền điện", water = "Tiền nước";
            for(ApartmentServiceConstract s : apartmentRental.getApartmentServiceConstractSet())
            {
                ApartmentDetailReceipt temp = new ApartmentDetailReceipt();
                if(s.getServiceId().getName().contains(electric))
                {
                    temp.setContent(electric);
                    int price = s.getServiceId().getPrice()*amount_electric;
                    temp.setPrice(price);
                    sum += price;
                } else if(s.getServiceId().getName().contains(water))
                {
                    temp.setContent(water);
                    temp.setPrice(s.getServiceId().getPrice() * amount_water);
                    sum += s.getServiceId().getPrice() * amount_water;
                }else
                {
                    temp.setContent(s.getServiceId().getName());
                    temp.setPrice(s.getServiceId().getPrice());
                    sum += s.getServiceId().getPrice();
                }
                temp.setReceiptId(receipt);
                detailReceiptService.createDetailByReceiptId(temp);
            }
            receipt.setTotal(sum);
            receiptService.updateReceipt(receipt);
            
            //update usage
            ApartmentUsageNumber electric_usage = new ApartmentUsageNumber();
            electric_usage.setType(ApartmentUsageNumber.Type.ELECTRIC.toString());
            electric_usage.setReceiptId(receipt);
            electric_usage.setNumber(cur_electric);
            usageNumberService.saveOrUpdate(electric_usage);
            
            ApartmentUsageNumber water_usage = new ApartmentUsageNumber();
            water_usage.setType(ApartmentUsageNumber.Type.WATER.toString());
            water_usage.setReceiptId(receipt);
            water_usage.setNumber(cur_water);
            usageNumberService.saveOrUpdate(water_usage);
            
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
           return new ResponseEntity<>("Thiếu các thông tin cần thiết", HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping(value = "/api/receipt/{id}", produces = "application/json")
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


