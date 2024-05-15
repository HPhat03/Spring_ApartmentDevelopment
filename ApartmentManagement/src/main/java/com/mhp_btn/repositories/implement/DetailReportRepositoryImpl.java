package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentDetailReport;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.repositories.DetailReportRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class DetailReportRepositoryImpl implements DetailReportRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;


    @Override
    public ApartmentDetailReport getDetailReportByReportId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();

        Query<ApartmentDetailReport> query = session.createQuery(
                "FROM ApartmentDetailReport a WHERE a.reportId.id = :reportId",
                ApartmentDetailReport.class
        );
        query.setParameter("reportId", id);
        ApartmentDetailReport result = query.uniqueResult();
        return result;
    }

    @Override
    public void addDetailReport(ApartmentDetailReport newDetail) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(newDetail);
    }

    @Override
    public ApartmentDetailReport getDetailReportById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("ApartmentDetailReport.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentDetailReport> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void updateDetailReport(ApartmentDetailReport detailReport) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(detailReport);
    }

    @Override
    public void deteleDetailReport(ApartmentDetailReport detailReport) {
        Session session = factoryBean.getObject().getCurrentSession();
//        ApartmentDetailReport detailReport = session.get(ApartmentDetailReport.class, id);

        session.delete(detailReport);

    }

}
