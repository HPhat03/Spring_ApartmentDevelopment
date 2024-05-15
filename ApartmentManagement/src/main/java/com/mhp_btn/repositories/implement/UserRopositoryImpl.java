/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.repositories.UserRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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
    private LocalSessionFactoryBean factory;

    @Override
    public List<ApartmentUser> getUsers() {
       Session s = this.factory.getObject().getCurrentSession();
       Query q = s.createNamedQuery("ApartmentUser.findAll");
       return q.getResultList();
    }

    @Override
    public ApartmentUser getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM ApartmentUser U WHERE U.username=:un");
        q.setParameter("un", username);
        return (ApartmentUser) q.getSingleResult();
    }

    @Override
    public ApartmentUser getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentUser.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentUser> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void save(ApartmentUser user) {
        Session s = this.factory.getObject().getCurrentSession();
        System.out.println(user.getId());
        if (user.getId() != null)
        {
            System.out.println("updating");
             s.update(user);
        }
        else
            s.save(user);
    }
    
}
