/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailRequest;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.repositories.StatRepository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.*;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Admin
 */
@Repository
@Transactional
public class StatRepositoryImpl implements StatRepository {

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

        q.multiselect(rRq.get("id"), rDRq.get("question"), rDRq.get("scoreBand"),
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
    public List<Object[]> statsRevenueByPeriod(int year, String period) {
        Session session = this.session.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<ApartmentReceipt> apartmentReceiptRoot = criteriaQuery.from(ApartmentReceipt.class);

        // Lấy thuộc tính month và year của ApartmentReceipt
        Expression<Integer> monthExpression = apartmentReceiptRoot.get("month");
        Expression<String> yearExpression = apartmentReceiptRoot.get("year");

        criteriaQuery.multiselect(
                monthExpression,
                criteriaBuilder.sum(apartmentReceiptRoot.get("total"))
        );
        

        Predicate yearPredicate = criteriaBuilder.equal(yearExpression, String.valueOf(year));
        criteriaQuery.where(yearPredicate);

        criteriaQuery.groupBy(monthExpression);
        criteriaQuery.orderBy(criteriaBuilder.asc(monthExpression));
        Query query = session.createQuery(criteriaQuery);
       
        return query.getResultList();
    }


}
