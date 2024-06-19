package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.*;
import com.mhp_btn.repositories.SurveyRequestRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
@PropertySource("classpath:configs.properties")
public class SurveyRequestRepositoryImpl implements SurveyRequestRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;

    @Override
    public List<ApartmentSurveyRequest> getAllSurveyRequest(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentSurveyRequest> cq = cb.createQuery(ApartmentSurveyRequest.class);
        Root<ApartmentSurveyRequest> r = cq.from(ApartmentSurveyRequest.class);
        cq.select(r);
        List<Predicate> preds = new ArrayList<>();


        cq.where(preds.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(r.get("id")));

        Query q = s.createQuery(cq);

        // phan trang
        String page = params.get("page");
        if(page != null && !page.isEmpty() ){
            int pagesize = Integer.parseInt(env.getProperty("survey.pagesize"));
            int start = (Integer.parseInt(page)-1) * pagesize;
            q.setFirstResult(start);
            q.setMaxResults(pagesize);
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

    @Override
    public long countSurvey() {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(ApartmentSurveyRequest.class)));
        Query rq = session.createQuery(q);
        return (long) rq.getSingleResult();
    }
}
