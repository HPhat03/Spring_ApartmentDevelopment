package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.repositories.RentalConstractRepository;
import java.util.ArrayList;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RentalConstractRepositoryImpl implements RentalConstractRepository {
    @Autowired
    private  LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;
    @Override
    public List<ApartmentRentalConstract> getAllRentalConstract(Map<String,String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentRentalConstract> cq = cb.createQuery(ApartmentRentalConstract.class);
        Root r = cq.from(ApartmentRentalConstract.class);
        cq.select(r);
        List<Predicate> preds = new ArrayList<>();

        String room_id = params.get("room");
        if(room_id!=null){
            System.out.println("HIIIII" + " " + room_id);
            Root room = cq.from(ApartmentRoom.class);
            preds.add(cb.like(room.get("roomNumber"), String.format("%%%s%%", room_id)));
            preds.add(cb.equal(r.get("roomId"), room.get("id")));
        }
        String resident = params.get("resident");
        if(resident!=null){
            Root rr = cq.from(ApartmentResident.class);
            Root ru = cq.from(ApartmentUser.class);
            preds.add(cb.equal(r.get("residentId"), rr.get("userId")));
            preds.add(cb.equal(rr.get("userId"), ru.get("id")));
            preds.add(cb.or(cb.like(ru.get("firstName"), String.format("%%%s%%", resident)), cb.like(ru.get("lastName"), String.format("%%%s%%", resident)), cb.equal(ru.get("username"), resident) ));
        }
        
        cq.where(preds.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(r.get("id")));
        Query query = s.createQuery(cq);
        //phan trang
        String page = params.get("page");
        if(page != null && !page.isEmpty() ){
            int pagesize = Integer.parseInt(Objects.requireNonNull(env.getProperty("contracts.pagesize")));
            int start = (Integer.parseInt(page)-1) * pagesize;
            query.setFirstResult(start);
            query.setMaxResults(pagesize);
        }


        return (List<ApartmentRentalConstract>) query.getResultList();
    }

    @Override
    public void deleteRentalConstractById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentRentalConstract apartmentRentalConstract = session.get(ApartmentRentalConstract.class, id);
        if (apartmentRentalConstract != null) {
            apartmentRentalConstract.setIsActive((short) 0);
            session.update(apartmentRentalConstract);
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

    @Override
    public boolean checkRenter(int id, String username) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentRentalConstract> cq = cb.createQuery(ApartmentRentalConstract.class);
        Root r = cq.from(ApartmentRentalConstract.class);
        Root rr = cq.from(ApartmentResident.class);
        Root ru = cq.from(ApartmentUser.class);

        cq.select(r);
        
        List<Predicate> preds = new ArrayList<>();
        preds.add(cb.equal(r.get("residentId"), rr.get("userId")));
        preds.add(cb.equal(rr.get("userId"), ru.get("id")));
        
        preds.add(cb.equal(r.get("id"), id));
        preds.add(cb.equal(ru.get("username"), username));
        
        cq.where(preds.toArray(Predicate[]::new));

        Query q = s.createQuery(cq);
        return !q.getResultList().isEmpty();
    }
    public long countConstracts() {

            Session session = this.factoryBean.getObject().getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> q = cb.createQuery(Long.class);
            q.select(cb.count(q.from(ApartmentRentalConstract.class)));
            Query rq = session.createQuery(q);
            return (long) rq.getSingleResult();

    }


}
