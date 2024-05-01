package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.repositories.FloorRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class FloorRepositoryImpl implements FloorRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public ApartmentFloor getFloorById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return  s.get(ApartmentFloor.class, id);
    }

    @Override
    public List<ApartmentFloor> getAllFloor() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentFloor.findAll");
        return q.getResultList();
    }

    @Override
    public void addFloor(ApartmentFloor floor) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(floor);
    }

    @Override
    public void deleteFloorById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentFloor floor = session.get(ApartmentFloor.class, id);
        if (floor != null) {
            session.delete(floor);
        } else {
            throw new IllegalArgumentException("Không tìm thấy tầng với ID: " + id);
        }
    }

    @Override
    public void updateFloor(ApartmentFloor floor) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(floor);
    }
}