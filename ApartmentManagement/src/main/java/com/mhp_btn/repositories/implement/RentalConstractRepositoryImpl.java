package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.repositories.RentalConstractRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class RentalConstractRepositoryImpl implements RentalConstractRepository {
    @Autowired
    private  LocalSessionFactoryBean factoryBean;
    @Override
    public List<ApartmentRentalConstract> getAllRentalConstract() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRentalConstract.findAll");
        return q.getResultList();
    }

    @Override
    public void deleteRentalConstractById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentRentalConstract apartmentRentalConstract = session.get(ApartmentRentalConstract.class, id);
        if (apartmentRentalConstract != null) {
            apartmentRentalConstract.setIsActive((short) 0); // Đặt isActive = 0
            session.update(apartmentRentalConstract); // Cập nhật thay đổi vào cơ sở dữ liệu
        } else {
            throw new IllegalArgumentException("Không tìm thấy hợp đồng với ID: " + id);
        }
    }

    @Override
    public ApartmentRentalConstract getRentalConstractById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRentalConstract.findById");
        q.setParameter("id", id); // Bind giá trị của tham số id vào câu truy vấn
        List<ApartmentRentalConstract> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void addRentalConstract(ApartmentRentalConstract constract) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(constract);
    }

    @Override
    public ApartmentRentalConstract getConstractById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRentalConstract.findById");
        q.setParameter("id", id);
        List<ApartmentRentalConstract> resultList = q.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }


    @Override
    public void updateConstract(ApartmentRentalConstract constract) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(constract);
    }


}
