/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentPaidPicture;
import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.repositories.PaymentRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class PaymentRepositoryImpl implements PaymentRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public void addOrUpdate(ApartmentPayment p) {
        Session s = this.factory.getObject().getCurrentSession();
        if(p.getId() != null)
            s.update(p);
        else
            s.save(p);
    }

    @Override
    public void addPictureMoMo(ApartmentPaidPicture p) {
        Session s = this.factory.getObject().getCurrentSession();
        if(p.getId() > 0)
            s.update(p);
        else
            s.save(p);
    }

    @Override
    public ApartmentPayment getPaymentByReceiptID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentPayment> criteriaQuery = criteriaBuilder.createQuery(ApartmentPayment.class);
        Root<ApartmentPayment> root = criteriaQuery.from(ApartmentPayment.class);
        Root<ApartmentReceipt> rc = criteriaQuery.from(ApartmentReceipt.class);
        
        List<Predicate> preds = new ArrayList<>();
        
        Predicate condition = criteriaBuilder.equal(root.get("receipt"), id);
//        preds.add(criteriaBuilder.equal(root.get("receipt"), rc.get("id")))
        
        criteriaQuery.select(root).where(condition);

        return s.createQuery(criteriaQuery).getSingleResult();
    }
    
}
