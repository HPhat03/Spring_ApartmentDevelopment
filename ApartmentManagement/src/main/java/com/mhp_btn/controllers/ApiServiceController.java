package com.mhp_btn.controllers;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiServiceController {

    @Autowired
    private ServiceService ss;

    @DeleteMapping("/services/{id}")
    public ResponseEntity<String> deleteServiceById(@PathVariable int id) {

        ApartmentService service = ss.getServiceById(id);
        if (service == null) {
            return new ResponseEntity<>("Không tìm thấy dịch vụ với ID " + id, HttpStatus.NOT_FOUND);
        }
        ss.deleteServiceById(id);
        return new ResponseEntity<>("Đã xóa phòng có ID " + id, HttpStatus.OK);
    }

}
