/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailRequest;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.repositories.StatRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
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
public class StatRepositoryImpl implements StatRepository{
    
    @Autowired
    private LocalSessionFactoryBean session;
    @Override
    public List<Object[]> statSurveybyId(int id) {
        Session s = this.session.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
        
        Root rRq = q.from(ApartmentSurveyRequest.class);
        Root rDRq = q.from(ApartmentDetailRequest.class);
        Root rDRp = q.from(ApartmentDetailResponse.class);
        
        q.multiselect(rRq.get("id"),rDRq.get("question"), rDRq.get("scoreBand"),
                        cb.avg(rDRp.get("answer")), cb.count(rDRp.get("id")));
        
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(rRq.get("id"), id));
        predicates.add(cb.equal(rRq.get("id"), rDRq.get("requestId")));
        predicates.add(cb.equal(rDRq.get("id"), rDRp.get("questionId")));
        
        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(rDRq.get("id"));
        Query query = s.createQuery(q);
        
        return query.getResultList();
    }

    @Override
    public List<Object[]> statRevenue(int year, String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}