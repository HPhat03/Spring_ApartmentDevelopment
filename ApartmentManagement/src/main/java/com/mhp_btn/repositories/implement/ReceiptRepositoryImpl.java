package com.mhp_btn.repositories.implement;


import com.mhp_btn.pojo.ApartmentPayment;
//import com.mhp_btn.pojo.ApartmentPayment_;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRelativeRegistry;
import com.mhp_btn.repositories.ReceiptRepository;
import java.util.ArrayList;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.HashMap;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@Transactional
@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {
    @Autowired
    private  LocalSessionFactoryBean factoryBean;

    @Override
    public ApartmentReceipt getReceiptById(int id) {
        
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentReceipt.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentReceipt> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteReceiptById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentRelativeRegistry relativeRegistry = session.get(ApartmentRelativeRegistry.class, id);
        if (relativeRegistry != null) {
            session.delete(relativeRegistry);
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
    public List<ApartmentReceipt> getAllReceipt(HashMap<String, String> params) {
        
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentReceipt> q = cb.createQuery(ApartmentReceipt.class);
        Root r = q.from(ApartmentReceipt.class);
        q.select(r);
        
        List<Predicate> predicates =  new ArrayList<>();
        
        String month = params.get("month");
        if(month!=null && !month.isEmpty())
            predicates.add(cb.equal(r.get("month"), Integer.parseInt(month)));
        
        String year = params.get("year");
        if(year!=null && !year.isEmpty())
            predicates.add(cb.equal(r.get("year"), year));
        
        String filter = params.get("filter");
        if(filter!=null){
            Subquery<ApartmentPayment> sq = q.subquery(ApartmentPayment.class);
            Root<ApartmentPayment> sr = sq.from(ApartmentPayment.class);
            sq.select(sr).where(cb.equal(r.get("id"), sr.get("receipt")));
            if(filter.equals("PAID"))
            {            
                predicates.add(cb.exists(sq));   
            }else if(filter.equals("UNPAID")){
                predicates.add(cb.not(cb.exists(sq)));
            }
        }
        
        
        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(cb.desc(r.get("id")));
        
        Query rs = s.createQuery(q);
        List<ApartmentReceipt> receipts = rs.getResultList();
        
        return receipts;
    }
}
