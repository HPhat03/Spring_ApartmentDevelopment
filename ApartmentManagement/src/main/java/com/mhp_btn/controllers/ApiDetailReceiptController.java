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

    //Lây các chi tiết theo id hóa đơn
    @GetMapping(path="/receipt/{receiptId}/details", produces = "application/json")
    public ResponseEntity<List<ApartmentDetailReceipt>> getDetailReceiptsByReceiptId(@PathVariable int receiptId) {
        List<ApartmentDetailReceipt> detailReceipts = detailService.getDetailReceiptsByReceiptId(receiptId);
        return new ResponseEntity<>(detailReceipts, HttpStatus.OK);
    }

    //Tạo các chi tiết theo Id hóa đơn
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
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //Xóa chi tiết hóa đơn id
    @DeleteMapping("/receipt/{receiptId}/details/{detailId}")
    public ResponseEntity<?> deleteDetailReceiptByReceiptId(@PathVariable("receiptId") int receiptId, @PathVariable("detailId") int detailId) {
        // Kiểm tra xem hóa đơn tồn tại hay không
        List<ApartmentDetailReceipt> detail = detailService.getDetailReceiptsByReceiptId(receiptId);
        if(detail.isEmpty()) {
            return new ResponseEntity<>("Receipt not found with ID: " + receiptId, HttpStatus.NOT_FOUND);
        }

        // Kiểm tra xem chi tiết hóa đơn tồn tại hay không
        boolean detailExists = false;
        for(ApartmentDetailReceipt d : detail) {
            if(d.getId() == detailId) {
                detailExists = true;
                break;
            }
        }

        if(!detailExists) {
            return new ResponseEntity<>("Detail receipt not found with ID: " + detailId, HttpStatus.NOT_FOUND);
        }

        detailService.deleteDetailReceiptById(detailId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    // Cập nhật chi tiết hóa đơn theo id của hóa đơn và chi tiết hóa đơn
    @PatchMapping(value="/receipt/{receiptId}/details/{detailId}" , produces = "application/json")
    public ResponseEntity<?> updateDetailReceiptByReceiptId(@PathVariable("receiptId") int receiptId, @PathVariable("detailId") int detailId, @RequestBody ApartmentDetailReceipt updatedDetail) {
        // Kiểm tra xem chi tiết hóa đơn cần cập nhật có tồn tại không
        ApartmentDetailReceipt existingDetail = detailService.getDetailReceiptById(detailId);
        if(existingDetail == null) {
            return new ResponseEntity<>("Detail receipt not found with ID: " + detailId, HttpStatus.NOT_FOUND);
        }

        // Kiểm tra xem chi tiết hóa đơn thuộc hóa đơn nào
        if(existingDetail.getReceiptId().getId() != receiptId) {
            return new ResponseEntity<>("Detail receipt does not belong to receipt with ID: " + receiptId, HttpStatus.BAD_REQUEST);
        }

        // Cập nhật thông tin của chi tiết hóa đơn
        existingDetail.setContent(updatedDetail.getContent());
        existingDetail.setPrice(updatedDetail.getPrice());

        // Lưu cập nhật vào cơ sở dữ liệu
        detailService.updateDetailReceipt(existingDetail);

        return new ResponseEntity<>(existingDetail, HttpStatus.OK);
    }
}
