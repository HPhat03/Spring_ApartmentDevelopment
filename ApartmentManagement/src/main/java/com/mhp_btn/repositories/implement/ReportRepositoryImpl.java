package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.repositories.ReportRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class ReportRepositoryImpl implements ReportRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentReport> getAllReportByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query<ApartmentReport> query = session.createQuery(
                "SELECT m FROM ApartmentReport m WHERE m.apartmentId.id = :apartmentId",
                ApartmentReport.class);
        query.setParameter("apartmentId", id);
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
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentReport> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
