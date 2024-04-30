/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.serializers.UserSerializer;
import com.mhp_btn.services.AdminService;
import com.mhp_btn.services.ResidentService;
import com.mhp_btn.services.UserService;
import com.mhp_btn.utils.ErrorHandle;
import com.mhp_btn.utils.StringUtil;
import com.mhp_btn.utils.TwilioUtil;
//import com.mhp_btn.utils.TwilioUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    private final SimpleDateFormat dateFormatter = StringUtil.dateFormater();
    @Autowired
    private UserService us;
    @Autowired
    private AdminService as;
    @Autowired
    private ResidentService rs;
    @Autowired
    private Cloudinary cloudinary;
    
    @GetMapping(path = "/test/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendSMS(){
        
        return ResponseEntity.ok("it worked! check it out");
    }

    @PostMapping(path = "/user/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    }, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> create(@RequestParam Map<String, String> data, @RequestPart MultipartFile[] file) throws ParseException, IOException {
        ApartmentUser u = new ApartmentUser();
        String firstname = data.get("firstname");
        String lastname = data.get("lastname");
        String phone = data.get("phone");
        Date today = new Date();
        
        u.setFirstName(firstname);
        u.setLastName(lastname);
        u.setPhone(phone);
        u.setGender((short) Integer.parseInt(data.get("gender")));
        u.setEmail(data.get("email"));
        u.setCreatedDate(today);
        u.setIsActive((short) 1);

        String role = data.get("role");
        if (role != null && role.equals(ApartmentUser.ADMIN)) {
            u.setRole(ApartmentUser.ADMIN);
            u.setUsername(data.get("username"));
            u.setPassword(data.get("password"));
            try {
                if (file.length > 0) {
                    Map res = cloudinary.uploader().upload(file[0].getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    u.setAvatar(res.get("secure_url").toString());
                }
            } catch (IOException e) {
                ErrorHandle error = new ErrorHandle("Không thể tải ảnh lên", HttpStatus.BAD_REQUEST, e.toString());
                return new ResponseEntity<>(error, error.getStatus());
            }

        } else {
            u.setRole(ApartmentUser.RESIDENT);
            String[] lastnameArr = lastname.split(" ");
            for (int i = 0; i < lastnameArr.length; i++) {
                lastnameArr[i] = lastnameArr[i].substring(0, 1);
            }
            String username = String.join("", lastnameArr) + firstname;
            u.setUsername(StringUtil.removeAccent(username).toLowerCase());
            u.setPassword(ApartmentUser.RESIDENT_DEFAULT_PASSWORD);
        }
        Date birthdate = this.dateFormatter.parse(data.get("birthdate"));
        u.setBirthdate(birthdate);

        try {
            us.save(u);
            if (u.getRole().equals(ApartmentUser.ADMIN)){
                ApartmentAdmin admin = new ApartmentAdmin(u.getId());
                admin.setApartmentUser(u);
                as.save(admin);
                u.setApartmentAdmin(admin);
            }else if (u.getRole().equals(ApartmentUser.RESIDENT)){
                ApartmentResident resident = new ApartmentResident(u.getId());
                resident.setApartmentUser(u);
                Date actualJoin = data.get("joineddate") == null ? new Date() : this.dateFormatter.parse(data.get("joineddate"));
                resident.setJoinedDate(actualJoin);
                rs.save(resident);
                u.setApartmentResident(resident);
            }
            us.save(u);
            
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
}
