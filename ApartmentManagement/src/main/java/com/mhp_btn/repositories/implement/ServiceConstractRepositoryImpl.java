package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.*;
import com.mhp_btn.repositories.ServiceConstractRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class ServiceConstractRepositoryImpl implements ServiceConstractRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentServiceConstract> getAllServiceConstract() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentServiceConstract.findAll");
        return q.getResultList();
    }

    @Override
    public ApartmentServiceConstract getServiceConstractById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentServiceConstract.findById");
        q.setParameter("id", id);
        List<ApartmentServiceConstract> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteServiceConstractById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentReport report = session.get(ApartmentReport.class, id);

        session.delete(report);

    }

    @Override
    public void addServiceConstract(ApartmentServiceConstract constract) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(constract);
    }

    @Override
    public void updateServiceConstract(ApartmentServiceConstract constract) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(constract);
    }

    @Override
    public List<ApartmentService> getServicesByApartmentId(int apartmentId) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentService> cq = cb.createQuery(ApartmentService.class);

        Root<ApartmentServiceConstract> root = cq.from(ApartmentServiceConstract.class);
        Join<ApartmentServiceConstract, ApartmentRentalConstract> apartmentJoin = root.join("apartmentId");
        Join<ApartmentServiceConstract, ApartmentService> serviceJoin = root.join("serviceId");

        cq.select(serviceJoin)
                .where(cb.equal(apartmentJoin.get("id"), apartmentId));
        Query query = s.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void deleteServiceConstractByApartmentId(int apartmentId) {
        Session session = factoryBean.getObject().getCurrentSession();

        String hql = "DELETE FROM ApartmentServiceConstract constract WHERE constract.apartmentId.id = :apartmentId";
        Query query = session.createQuery(hql);
        query.setParameter("apartmentId", apartmentId);
        int deletedCount = query.executeUpdate();
    }



}
