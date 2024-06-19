package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.repositories.ReportRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Transactional
@Repository
public class ReportRepositoryImpl implements ReportRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentReport> getAllReport() {
        Session s = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("ApartmentReport.findAll");
        return q.getResultList();
    }

    @Override
    public List<ApartmentReport> getAllReportByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ApartmentReport> cq = cb.createQuery(ApartmentReport.class);
        Root<ApartmentReport> root = cq.from(ApartmentReport.class);
        Predicate predicate = cb.equal(root.get("apartmentId").get("id"), id);
        cq.select(root).where(predicate);

        Query<ApartmentReport> query = session.createQuery(cq);
        return query.getResultList();
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
}
