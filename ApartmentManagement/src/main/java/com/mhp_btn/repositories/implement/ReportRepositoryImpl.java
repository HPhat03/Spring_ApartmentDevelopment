package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.repositories.ReportRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;
import java.util.Objects;

@Transactional
@Repository
@PropertySource("classpath:configs.properties")
public class ReportRepositoryImpl implements ReportRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<ApartmentReport> getAllReportByApartmentId(int id, int page) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query<ApartmentReport> query = session.createQuery(
                "SELECT m FROM ApartmentReport m WHERE m.apartmentId.id = :apartmentId ORDER BY m.id DESC",
                ApartmentReport.class);
        query.setParameter("apartmentId", id);
        
        if(page > 0 ){
            int pageSize = Integer.parseInt(env.getProperty("receipt.clientPageSize").toString());
            int start = (page - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }
    
    @Override
    public List<ApartmentReport> getAllReport(Map<String, String> params) {
        Session s = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentReport> q = b.createQuery(ApartmentReport.class);
        Root<ApartmentReport> r = q.from(ApartmentReport.class);

        q.select(r);
        List<Predicate> predicates = new ArrayList<>();

        q.where(predicates.toArray(new Predicate[0]));

        javax.persistence.Query query = s.createQuery(q);
        //phan trang
        String page = params.get("page");
        if(page != null && !page.isEmpty() ){
            int pagesize = Integer.parseInt(Objects.requireNonNull(env.getProperty("room.pagesize")));
            int start = (Integer.parseInt(page)-1) * pagesize;
            query.setFirstResult(start);
            query.setMaxResults(pagesize);
        }
        return (List<ApartmentReport>) query.getResultList();
    }


    @Override
    public void deleteReportById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentReport report = session.get(ApartmentReport.class, id);
        if (report != null) {
            session.delete(report);
        }
    }

    @Override
    public void addReport(ApartmentReport report) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(report);
    }

    @Override
    public void updateReport(ApartmentReport report) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(report);
    }

    @Override
    public ApartmentReport getReportById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentReport.findById");
        q.setParameter("id", id);
        List<ApartmentReport> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public long countReport() {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(ApartmentReport.class)));
        javax.persistence.Query rq = session.createQuery(q);
        return (long) rq.getSingleResult();
    }
}
