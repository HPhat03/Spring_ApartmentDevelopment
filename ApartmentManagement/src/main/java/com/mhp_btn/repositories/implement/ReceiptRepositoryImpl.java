package com.mhp_btn.repositories.implement;


import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.pojo.ApartmentReceipt;

// import com.mhp_btn.pojo.*;
//import com.mhp_btn.pojo.ApartmentPayment_;
import com.mhp_btn.repositories.ReceiptRepository;

import java.util.*;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Transactional
@Repository
@PropertySource("classpath:configs.properties")
public class ReceiptRepositoryImpl implements ReceiptRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public ApartmentReceipt getReceiptById(int id) {

        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentReceipt.findById");
        q.setParameter("id", id);
        List<ApartmentReceipt> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteReceiptById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentReceipt receipt = session.get(ApartmentReceipt.class, id);
        if (receipt != null) {
            session.delete(receipt);
        }
    }

    @Override
    public void addReceipt(ApartmentReceipt receipt) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(receipt);
    }

    @Override
    public void updateReceipt(ApartmentReceipt receipt) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(receipt);
    }

    @Override
    public List<ApartmentReceipt> getAll(Map<String, String> params) {
        Session s = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentReceipt> q = b.createQuery(ApartmentReceipt.class);
        Root<ApartmentReceipt> r = q.from(ApartmentReceipt.class);

        q.select(r);
        List<Predicate> predicates = new ArrayList<>();


        q.where(predicates.toArray(new Predicate[0]));
        q.orderBy(b.desc(r.get("id")));
        Query query = s.createQuery(q);
        //phan trang
        String page = params.get("page");
        if (page != null && !page.isEmpty()) {
            int pagesize = Integer.parseInt(Objects.requireNonNull(env.getProperty("room.pagesize")));
            int start = (Integer.parseInt(page) - 1) * pagesize;
            query.setFirstResult(start);
            query.setMaxResults(pagesize);
        }
        return (List<ApartmentReceipt>) query.getResultList();
    }

    @Override
    public long countReceipt() {

        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(ApartmentReceipt.class)));
        Query rq = session.createQuery(q);
        return (long) rq.getSingleResult();

    }

    @Override
    public List<ApartmentReceipt> getAllReceipt(HashMap<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentReceipt> q = cb.createQuery(ApartmentReceipt.class);
        Root<ApartmentReceipt> r = q.from(ApartmentReceipt.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String month = params.get("month");
        if (month != null && !month.isEmpty()) {
            predicates.add(cb.equal(r.get("month"), Integer.parseInt(month)));
        }

        String year = params.get("year");
        if (year != null && !year.isEmpty()) {
            predicates.add(cb.equal(r.get("year"), year));
        }

        String filter = params.get("filter");
        if (filter != null) {
            Subquery<ApartmentPayment> sq = q.subquery(ApartmentPayment.class);
            Root<ApartmentPayment> sr = sq.from(ApartmentPayment.class);
            sq.select(sr).where(cb.equal(r.get("id"), sr.get("receipt")));
            if (filter.equals("PAID")) {
                predicates.add(cb.exists(sq));
            } else if (filter.equals("UNPAID")) {
                predicates.add(cb.not(cb.exists(sq)));
            }
        }
        
        String apartment = params.get("apartment");
        if(apartment!=null && !apartment.isEmpty()){
            predicates.add(cb.equal(r.get("apartmentId"), Integer.parseInt(apartment)));
        }
        q.where(predicates.toArray(new Predicate[0]));
        q.orderBy(cb.desc(r.get("id")));

        Query rs = s.createQuery(q);
        String page = params.get("page");
        if(page!=null && !page.isEmpty())
        {
            
           int pageSize = Integer.parseInt(env.getProperty("receipt.clientPageSize"));
            int start = (Integer.parseInt(page) - 1) * pageSize;
            rs.setFirstResult(start);
            rs.setMaxResults(pageSize);
        }
        List<ApartmentReceipt> receipts = rs.getResultList();

        return receipts;
    }
        
}
