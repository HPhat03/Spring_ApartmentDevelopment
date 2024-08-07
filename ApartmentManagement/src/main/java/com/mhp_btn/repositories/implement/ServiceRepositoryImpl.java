/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.repositories.ServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ServiceRepositoryImpl implements ServiceRepository{
    @Autowired
    private LocalSessionFactoryBean f;
    @Autowired
    private Environment env;
    @Override
    public List<ApartmentService> getService(Map<String, String> params) {
        Session s = this.f.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentService> q = b.createQuery(ApartmentService.class);
        Root<ApartmentService> r = q.from(ApartmentService.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);

        // phan trang
        String page = params.get("page");
        if(page != null && !page.isEmpty() ){
            int pagesize = Integer.parseInt(env.getProperty("services.pagesize"));
            int start = (Integer.parseInt(page)-1) * pagesize;
            query.setFirstResult(start);
            query.setMaxResults(pagesize);
        }
        return (List<ApartmentService>) query.getResultList();
    }

    @Override
    public List<ApartmentService> getServiceActive() {
        Session s = Objects.requireNonNull(this.f.getObject()).getCurrentSession();
        Query q = s.createNamedQuery("ApartmentService.findByIsActive", ApartmentService.class);
        q.setParameter("isActive", (short) 1);
        return q.getResultList();
    }

    @Override
    public List<ApartmentService> getAllServices() {
        Session s = this.f.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentService.findAll");
        return q.getResultList();
    }

    @Override
    public void addOrUpdate(ApartmentService service) {
        Session s = this.f.getObject().getCurrentSession();
        if(service.getId() != null && service.getId() > 0) {
            s.update(service);
        } else {
            s.save(service);
        }
    }




    @Override
    public ApartmentService getServiceById(int id) {
        Session s = this.f.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentService.findById");
        q.setParameter("id", id);
        List<ApartmentService> result = q.getResultList();
        return result.isEmpty() ? null :result.get(0);

    }

    @Override
    public void deleteServiceById(int id) {
        Session s = this.f.getObject().getCurrentSession();
        ApartmentService sv = s.get(ApartmentService.class, id);
        if(sv!=null){
            s.delete(sv);
        }
    }

    @Override
    public void updateService(ApartmentService service) {
        Session session = this.f.getObject().getCurrentSession();
        session.update(service);
    }

    @Override
    public long countService() {
        Session session = this.f.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(ApartmentService.class)));
        Query rq = session.createQuery(q);
        return (long) rq.getSingleResult();
        
    }


}
