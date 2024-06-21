package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.repositories.SmartCabinetRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Transactional
@Repository
public class SmartCabinetRepositoryImpl implements SmartCabinetRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentSmartCabinet> getAllSmartCabinets(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ApartmentSmartCabinet> criteriaQuery = criteriaBuilder.createQuery(ApartmentSmartCabinet.class);
        Root<ApartmentSmartCabinet> root = criteriaQuery.from(ApartmentSmartCabinet.class);

        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();

        // Lấy tham số status từ params và tạo Predicate
        if (params.containsKey("status")) {
            String status = params.get("status");
            if ("RECEIVED'".equalsIgnoreCase(status)) {
                predicates.add(criteriaBuilder.equal(root.get("status"), "RECEIVED"));
            } else if ("PENDING".equalsIgnoreCase(status)) {
                predicates.add(criteriaBuilder.equal(root.get("status"), "PENDING"));
            }
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        Query query = session.createQuery(criteriaQuery);

//        // Phân trang
//        String page = params.get("page");
//        if (page != null && !page.isEmpty()) {
//            int pagesize = Integer.parseInt(Objects.requireNonNull(env.getProperty("services.pagesize")));
//            int start = (Integer.parseInt(page) - 1) * pagesize;
//            query.setFirstResult(start);
//            query.setMaxResults(pagesize);
//        }

        return query.getResultList();
    }


    @Override
    public List<ApartmentSmartCabinet> getAllSmartCabinetByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        org.hibernate.query.Query<ApartmentSmartCabinet> query = session.createQuery(
                "SELECT m FROM ApartmentSmartCabinet m WHERE m.apartmentId.id = :apartmentId",
                ApartmentSmartCabinet.class);
        query.setParameter("apartmentId", id);
        return query.getResultList();
    }

    @Override
    public ApartmentSmartCabinet getSmartCabinetById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSmartCabinet.findById");
        q.setParameter("id", id); // Thiết lập giá trị của tham số ID
        List<ApartmentSmartCabinet> result = q.getResultList();
        return result.isEmpty() ? null :result.get(0);
    }

    @Override
    public void deleteCabinetById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentSmartCabinet report = session.get(ApartmentSmartCabinet.class, id);
        session.delete(report);

    }

    @Override
    public void addCabinet(ApartmentSmartCabinet cabinet) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(cabinet);
    }

    @Override
    public void updateCabinet(ApartmentSmartCabinet cabinet) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(cabinet);
    }

    @Override
    public void addOrUpdate(ApartmentSmartCabinet c) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        if (c.getId() != null && c.getId() > 0) {
            s.update(c);
        } else {
            s.save(c);
        }
    }



}
