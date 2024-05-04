package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.repositories.SurveyRequestRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class SurveyRequestRepositoryImpl implements SurveyRequestRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentSurveyRequest> getAllSurveyRequest() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSurveyRequest.findAll");
        return q.getResultList();
    }

    @Override
    public ApartmentSurveyRequest getSurveyRequestById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSurveyRequest.findById");
        q.setParameter("id", id); // Thiết lập giá trị của tham số ID
        List<ApartmentSurveyRequest> result = q.getResultList();
        return result.isEmpty() ? null :result.get(0);
    }

    @Override
    public void deleteSurveyRequestById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentSurveyRequest report = session.get(ApartmentSurveyRequest.class, id);
        session.delete(report);
    }

    @Override
    public void addSurveyRequest(ApartmentSurveyRequest request) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(request);
    }

    @Override
    public void updateSurveyRequest(ApartmentSurveyRequest surveyRequest) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(surveyRequest);
    }
}
