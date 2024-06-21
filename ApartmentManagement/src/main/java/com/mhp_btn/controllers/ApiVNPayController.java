/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.mhp_btn.configs.VnPayConfig;
import com.mhp_btn.pojo.ApartmentPaidPicture;
import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.pojo.VNPayCashier;
import com.mhp_btn.services.PaymentService;
import com.mhp_btn.services.ReceiptService;
import java.io.UnsupportedEncodingException;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiVNPayController {
    
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping(path = "/vnpay/create_payment/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> createPayment(@RequestParam Map<String,String> params) throws UnsupportedEncodingException{
        String orderType = "other";
        long amount = Integer.parseInt(params.get("amount")) * 100;
        String bankCode = "NCB";
        
        String vnp_TxnRef = VnPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "13.160.92.202";

        String vnp_TmnCode = VnPayConfig.vnp_TmnCode;
        
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VnPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VnPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VnPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        
        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");

        vnp_Params.put("vnp_ReturnUrl", String.format(VnPayConfig.vnp_ReturnUrl, params.get("receipt_id")));
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;
        
        VNPayCashier cashier = new VNPayCashier();
        cashier.setMessage("Đang chuyển trình xử lý");
        cashier.setStatus("Successful");
        cashier.setUrl(paymentUrl);
        return ResponseEntity.ok(cashier);
    }
    @PostMapping(path = "/validate_transaction/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> validate(@RequestBody Map<String,String> params){
        if(!params.containsKey("receiptId") || !params.containsKey("type") || !params.containsKey("transactionId") || (params.get("type").equals("MM_PIC") && !params.containsKey("MoMoPicture")))
            return new ResponseEntity<>("Thiếu thông tin", HttpStatus.OK);
        ApartmentPayment pay = new ApartmentPayment();
        pay.setCreatedDate(new Date());
        pay.setPaymentMethod(params.get("type"));
        pay.setTransactionId(params.get("transactionId"));
        pay.setReceipt(receiptService.getReceiptById(Integer.parseInt(params.get("receiptId"))));
        
        paymentService.SaveOrUpdate(pay);
        
        if(pay.getPaymentMethod().equals("MM_PIC") )
        {
            ApartmentPaidPicture p = new ApartmentPaidPicture();
            p.setPaymentId(pay);
            p.setPictureUrl(params.get("MoMoPicture"));
            paymentService.addPictureMoMo(p);
        }
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
