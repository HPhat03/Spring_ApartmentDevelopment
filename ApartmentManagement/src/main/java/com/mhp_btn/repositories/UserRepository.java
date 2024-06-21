/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentUser;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Admin
 */
public interface UserRepository{
    void save(ApartmentUser user);
    List<ApartmentUser> getUsers(Map<String, String> params) ;
    ApartmentUser getUsersByID(int id);
    ApartmentUser getUserByUsername(String username);
    ApartmentUser ChangeOrInitialize(ApartmentUser user, Map<String,String> data, boolean isInit);
    boolean authResident(String username, String password);
     boolean authUser(String username, String password);

    public void deleteUserById(int id) ;

    List<ApartmentUser> getUserByRole( String role);

    public long countUser() ;
}
