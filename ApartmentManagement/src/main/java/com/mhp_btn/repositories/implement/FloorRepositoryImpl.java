package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.repositories.FloorRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
