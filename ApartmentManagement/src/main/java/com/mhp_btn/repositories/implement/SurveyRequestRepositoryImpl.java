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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Transactional
@Repository
@PropertySource("classpath:config.properties")
public class SurveyRequestRepositoryImpl implements SurveyRequestRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;
    @Override
    public List<ApartmentSurveyRequest> getAllSurveyRequest(int page) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSurveyRequest.findAll");
        if(page > 0 ){
            int pageSize = Integer.parseInt(env.getProperty("receipt.clientPageSize").toString());
            int start = (page - 1) * pageSize;
            q.setFirstResult(start);
            q.setMaxResults(pageSize);
        }
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
