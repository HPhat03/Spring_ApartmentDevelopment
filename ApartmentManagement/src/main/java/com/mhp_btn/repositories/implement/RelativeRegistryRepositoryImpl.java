package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRelativeRegistry;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.repositories.RelativeRegistryRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class RelativeRegistryRepositoryImpl implements RelativeRegistryRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public List<ApartmentRelativeRegistry> getAllRelativeRegistry() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRelativeRegistry.findAll");
        return q.getResultList();
    }

    @Override
    public ApartmentRelativeRegistry getRelativeRegistryById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRelativeRegistry.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentRelativeRegistry> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteRelativeRegistryById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentRelativeRegistry relativeRegistry = session.get(ApartmentRelativeRegistry.class, id);
        if (relativeRegistry != null) {
            relativeRegistry.setActive((short) 0); // Đặt isActive = 0
            session.update(relativeRegistry); // Cập nhật thay đổi vào cơ sở dữ liệu
        }
    }

    @Override
    public void addRelativeRegistry(ApartmentRelativeRegistry relativeRegistry) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(relativeRegistry);
    }

    @Override
    public void updateRelativeRegistry(ApartmentRelativeRegistry relativeRegistry) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(relativeRegistry);
    }


}
