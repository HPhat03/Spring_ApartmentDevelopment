/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentUser;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface UserService extends UserDetailsService {
    List<ApartmentUser> getUsers();
    ApartmentUser getUserByID(int id);
    ApartmentUser getUsersByUsername(String username);
    void save(ApartmentUser user, boolean resetPassword);
    ApartmentUser ChangeOrInitialize(ApartmentUser user, Map<String, String> data, boolean isInit);
}
