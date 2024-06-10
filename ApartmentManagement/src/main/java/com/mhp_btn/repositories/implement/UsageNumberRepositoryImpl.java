/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentUsageNumber;
import com.mhp_btn.repositories.UsageNumberRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class UsageNumberRepositoryImpl implements UsageNumberRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void addOrUpdate(ApartmentUsageNumber un) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        if (un.getId() == null)
            s.save(un);
        else
            s.update(un);
    }

    @Override
    public List<ApartmentUsageNumber> lastMonthUsage(int ApartmentId, int month, String year) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentUsageNumber> q = cb.createQuery(ApartmentUsageNumber.class);
        Root rc = q.from(ApartmentReceipt.class);
        Root run = q.from(ApartmentUsageNumber.class);
        
        q.select(run);
        q.where(cb.and(cb.equal(rc.get("apartmentId"), ApartmentId),
                                cb.equal(rc.get("month"), month-1),
                                cb.equal(rc.get("year"), year)));
        Query res = s.createQuery(q);
        return res.getResultList();
    }
    
}
