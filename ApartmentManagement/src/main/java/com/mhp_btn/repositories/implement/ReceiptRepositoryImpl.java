package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRelativeRegistry;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.repositories.ReceiptRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {
    @Autowired
    private  LocalSessionFactoryBean factoryBean;
    @Override
    public List<ApartmentReceipt> getAllReceipt() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentReceipt.findAll");
        return q.getResultList();
    }

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
}
