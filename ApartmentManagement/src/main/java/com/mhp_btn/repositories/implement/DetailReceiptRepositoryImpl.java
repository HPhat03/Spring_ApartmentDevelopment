package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.repositories.DetailReceiptRepository;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.criteria.*;

@Transactional
@Repository
public class DetailReceiptRepositoryImpl implements DetailReceiptRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;


    @Override
    public List<ApartmentDetailReceipt> getDetailReceiptsByReceiptId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ApartmentDetailReceipt> cq = cb.createQuery(ApartmentDetailReceipt.class);
        Root<ApartmentDetailReceipt> root = cq.from(ApartmentDetailReceipt.class);

        Join<ApartmentDetailReceipt, ApartmentReceipt> joinReceipt = root.join("receiptId");

        Predicate predicate = cb.equal(joinReceipt.get("id"), id);
        cq.where(predicate);
        return session.createQuery(cq).getResultList();
    }

    @Override
    public void saveDetailReceipt(ApartmentDetailReceipt detailReceipt) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(detailReceipt);
    }

    @Override
    public void deleteDetailReceiptById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentDetailReceipt detailReceipt = session.get(ApartmentDetailReceipt.class, id);
        if (detailReceipt != null) {
            session.delete(detailReceipt);
        }
    }

    @Override
    public ApartmentDetailReceipt getDetailReceiptById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("ApartmentDetailReceipt.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentDetailReceipt> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void updateDetailReceipt(ApartmentDetailReceipt detailReceipt) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(detailReceipt);
    }



}
