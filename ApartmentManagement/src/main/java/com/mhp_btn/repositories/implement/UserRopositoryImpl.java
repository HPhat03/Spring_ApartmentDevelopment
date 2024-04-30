/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.repositories.UserRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserRopositoryImpl implements UserRepository{
    
    @Autowired
    private SessionFactory factory;
    @Override
    public void addUser(ApartmentUser user) {
        Session s = this.factory.getCurrentSession();
        s.save(user);
    }

    @Override
    public List<ApartmentUser> getUsers() {
       Session s = this.factory.getCurrentSession();
       Query q = s.createNamedQuery("ApartmentUser.findAll");
       return q.getResultList();
    }

    @Override
    public ApartmentUser getUserByUsername(String username) {
        Session s = this.factory.getCurrentSession();
        Query q = s.createQuery("FROM ApartmentUser U WHERE U.username=:un");
        q.setParameter("un", username);
        return (ApartmentUser) q.getSingleResult();
    }
    
}
