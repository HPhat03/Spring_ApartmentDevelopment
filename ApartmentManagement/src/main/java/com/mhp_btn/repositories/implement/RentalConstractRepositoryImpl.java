package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.repositories.RentalConstractRepository;
import java.util.ArrayList;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class RentalConstractRepositoryImpl implements RentalConstractRepository {
    @Autowired
    private  LocalSessionFactoryBean factoryBean;
    @Override
    public List<ApartmentRentalConstract> getAllRentalConstract(Map<String,String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentRentalConstract> cq = cb.createQuery(ApartmentRentalConstract.class);
        Root<ApartmentRentalConstract> r = cq.from(ApartmentRentalConstract.class);
        cq.select(r);
        List<Predicate> preds = new ArrayList<>();

        String room_id = params.get("room");
        if(room_id!=null){
            Root room = cq.from(ApartmentRoom.class);
            preds.add(cb.like(room.get("roomNumber"), String.format("%%%s%%", room_id)));
            preds.add(cb.equal(r.get("roomId"), room.get("id")));
        }
        
        cq.where(preds.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(r.get("id")));

        Query q = s.createQuery(cq);
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
        return (ApartmentRentalConstract) q.getSingleResult();
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
