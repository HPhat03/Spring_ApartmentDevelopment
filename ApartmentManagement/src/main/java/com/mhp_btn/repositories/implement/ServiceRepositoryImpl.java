/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.repositories.ServiceRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ServiceRepositoryImpl implements ServiceRepository{
    @Autowired
    private LocalSessionFactoryBean f;
    @Override
    public List<ApartmentService> getService() {
        Session s = this.f.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentService.findAll");
        return q.getResultList();
    }

    @Override
    public void addService(ApartmentService service) {
        Session session = f.getObject().getCurrentSession();
        session.save(service);
    }

    @Override
    public ApartmentService getServiceById(int id) {
        Session s = this.f.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentService.findById");
        q.setParameter("id", id); // Thiết lập giá trị của tham số ID
        List<ApartmentService> result = q.getResultList();
        return result.isEmpty() ? null :result.get(0);

    }

    @Override
    public void deleteServiceById(int id) {
        Session s = f.getObject().getCurrentSession();
        ApartmentService service = s.get(ApartmentService.class, id);
        if (service != null) {
            // Cập nhật cờ isActive thành 0
            service.setIsActive((short) 0);
            s.update(service);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with id: " + id);
        }
    }

    @Override
    public void updateService(ApartmentService service) {
        Session session = this.f.getObject().getCurrentSession();
        session.update(service);
    }


}
