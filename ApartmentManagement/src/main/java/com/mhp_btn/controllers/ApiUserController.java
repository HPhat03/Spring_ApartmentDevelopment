/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;


import com.mhp_btn.components.JwtService;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.serializers.UserSerializer;
import com.mhp_btn.services.AdminService;
import com.mhp_btn.services.ResidentService;
import com.mhp_btn.services.UserService;
import com.mhp_btn.utils.ErrorHandle;
import com.mhp_btn.utils.StringUtil;
//import com.mhp_btn.utils.TwilioUtil;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Admin
 */
@RestController
@CrossOrigin
public class ApiUserController {

    private final SimpleDateFormat dateFormatter = StringUtil.dateFormater();
    @Autowired
    private UserService us;
    @Autowired
    private AdminService as;
    @Autowired
    private ResidentService rs;
    @Autowired
    private JwtService jwtService;

    //    @GetMapping(path = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<MappingJacksonValue> list(){
//        List<ApartmentUser> users = this.us.getUsers();
//        return new ResponseEntity<>(UserSerializer.UserList(users),HttpStatus.OK);
//
//    }
    @DeleteMapping("/users/{id}/")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        ApartmentUser u = this.us.getUserByID(id);
        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        us.deleteUserById(id);
        return new ResponseEntity<>("Đã xóa phòng có ID " + id, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/users/add/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> create(@RequestParam Map<String, String> data, @RequestPart MultipartFile[] file) throws ParseException, IOException {
        ApartmentUser u = new ApartmentUser();
        if (file.length > 0) {
            u.setFile(file[0]);
        }
        u = us.ChangeOrInitialize(u, data, true);
        if (u.getFirstName() == null) {
            ErrorHandle error = new ErrorHandle("Không thể upload hình ảnh", HttpStatus.BAD_REQUEST, "ERROR UPLOADING TO CLOUDINARY");
            return new ResponseEntity<>(error, error.getStatus());
        }
        try {
            us.save(u, true);
            if (u.getRole().equals(ApartmentUser.ADMIN)) {
                ApartmentAdmin admin = new ApartmentAdmin(u.getId());
                admin.setApartmentUser(u);
                as.save(admin);
                u.setApartmentAdmin(admin);
            } else if (u.getRole().equals(ApartmentUser.RESIDENT)) {
                ApartmentResident resident = new ApartmentResident(u.getId());
                resident.setApartmentUser(u);
                Date actualJoin = data.get("joineddate") == null ? new Date() : this.dateFormatter.parse(data.get("joineddate"));
                resident.setJoinedDate(actualJoin);
                rs.save(resident);
                u.setApartmentResident(resident);
            }
            us.save(u, false);

        } catch (Exception e) {
            ErrorHandle error = new ErrorHandle("Tạo không thành công", HttpStatus.BAD_REQUEST, e.toString());
            return new ResponseEntity<>(error, error.getStatus());
        }
        //ĐÃ TEST THÀNH CÔNG -> KHI NÀO DEMO CHO THẦY RỒI MỞ.
//        TwilioUtil.SendSMS("+84365051699", String.format("\n[PN APARTMENT THÔNG BÁO]\nTài khoản của quý khách %s đã được tạo. Giờ đây quý khách có thể đăng nhập "
//                + "và sử dụng dịch vụ của chúng tôi.\nQuý khách vui lòng không tiết lộ thông tin đăng nhập của mình cho bất kì ai.\n"
//                + "Quý khách vui lòng đăng nhập lần đầu vào hệ thống theo username: %s.", u.getName(), u.getUsername()));
        return new ResponseEntity<>(UserSerializer.UserDetail(u), HttpStatus.CREATED);

    }
    
    @PatchMapping(path = "/api/user/{id}/", consumes = {
        MediaType.APPLICATION_JSON_VALUE
    },
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Map<String, String> data) {
        ApartmentUser u = this.us.getUserByID(id);
        if (u == null) {
            ErrorHandle error = new ErrorHandle("Không tìm thấy người dùng", HttpStatus.NOT_FOUND, "USER IS NULL");
            return new ResponseEntity<>(error, error.getStatus());
        }
        u = us.ChangeOrInitialize(u, data, false);
        if (u.getFirstName() == null) {
            ErrorHandle error = new ErrorHandle("Không thể upload hình ảnh", HttpStatus.BAD_REQUEST, "ERROR UPLOADING TO CLOUDINARY");
            return new ResponseEntity<>(error, error.getStatus());
        }

        try {
            us.save(u, false);
        } catch (Exception e) {
            ErrorHandle error = new ErrorHandle("Tạo không thành công", HttpStatus.BAD_REQUEST, e.toString());
            return new ResponseEntity<>(error, error.getStatus());
        }
        return new ResponseEntity<>(UserSerializer.UserDetail(u), HttpStatus.OK);
    }
    
    // @PatchMapping(path = "/user/{id}", consumes = {
    //         MediaType.APPLICATION_JSON_VALUE
    // },
    //         produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody Map<String, String> data) {
    //     ApartmentUser u = this.us.getUserByID(id);
    //     if (u == null) {
    //         ErrorHandle error = new ErrorHandle("Không tìm thấy người dùng", HttpStatus.NOT_FOUND, "USER IS NULL");
    //         return new ResponseEntity<>(error, error.getStatus());
    //     }
    //     u = us.ChangeOrInitialize(u, data, false);
    //     if (u.getFirstName() == null) {
    //         ErrorHandle error = new ErrorHandle("Không thể upload hình ảnh", HttpStatus.BAD_REQUEST, "ERROR UPLOADING TO CLOUDINARY");
    //         return new ResponseEntity<>(error, error.getStatus());
    //     }

    //     try {
    //         us.save(u, false);
    //     } catch (Exception e) {
    //         ErrorHandle error = new ErrorHandle("Tạo không thành công", HttpStatus.BAD_REQUEST, e.toString());
    //         return new ResponseEntity<>(error, error.getStatus());
    //     }
    //     return new ResponseEntity<>(UserSerializer.UserDetail(u), HttpStatus.OK);

    // }

    @PostMapping(path = "/api/user/{id}/add_avatar/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> AddAvatar(@PathVariable int id, @RequestPart MultipartFile file) {
        ApartmentUser u = this.us.getUserByID(id);
        if (u == null) {
            ErrorHandle error = new ErrorHandle("Không tìm thấy người dùng", HttpStatus.NOT_FOUND, "USER IS NULL");
            return new ResponseEntity<>(error, error.getStatus());
        }
        u.setFile(file);
        u = us.ChangeOrInitialize(u, new HashMap<>(), false);
        if (u.getAvatar()== null)
        {
            return new ResponseEntity<>("Không thể upload hình ảnh", HttpStatus.BAD_REQUEST);
        }
        ApartmentResident res = u.getApartmentResident();
        res.setFirstLogin((short) 0);
        rs.update(res);
        
        try{
           us.save(u, false); 
        }catch (Exception e)
        {
            ErrorHandle error = new ErrorHandle("Tạo không thành công", HttpStatus.BAD_REQUEST, e.toString());
            return new ResponseEntity<>(error, error.getStatus());
        }
        return new ResponseEntity<>(UserSerializer.UserDetail(u), HttpStatus.OK);
    }
    
    @PostMapping(path = "/api/login/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody ApartmentUser user) {
        
        if (this.us.authResident(user.getUsername(), user.getPassword()) == true ) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("INVALID RESIDENT", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/api/me/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<MappingJacksonValue> getCurrentUser(Principal p) {
        System.out.println(p.getName());
//        System.out.println(jwtService.getUsernameFromToken(request.getHeader("Authorization")));
        ApartmentUser u = this.us.getUsersByUsername(p.getName());
        System.out.println(u.getBirthdate());
        return new ResponseEntity<>(UserSerializer.UserDetail(u), HttpStatus.OK);
    }


}
