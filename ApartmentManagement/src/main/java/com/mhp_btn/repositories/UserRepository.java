/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentUser;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface UserRepository {
    void save(ApartmentUser user);
    List<ApartmentUser> getUsers();
    ApartmentUser getUsersByID(int id);
    ApartmentUser getUserByUsername(String username);
    ApartmentUser ChangeOrInitialize(ApartmentUser user, Map<String,String> data, boolean isInit);
}
