package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import com.mhp_btn.repositories.SurveyResponseRepository;
import com.mhp_btn.services.SurveyRequestService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class SurveyResponseRepositoryImpl implements SurveyResponseRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentSurveyResponse> getAllSurveyResponse() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSurveyResponse.findAll");
        return q.getResultList();
    }

    @Override
    public ApartmentSurveyResponse getSurveyResponseById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSurveyResponse.findById");
        q.setParameter("id", id); // Thiết lập giá trị của tham số ID
        List<ApartmentSurveyResponse> result = q.getResultList();
        return result.isEmpty() ? null :result.get(0);
    }

    @Override
    public List<ApartmentSurveyResponse> getAllBySurveyId(Integer surveyId) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentSurveyResponse> cq = cb.createQuery(ApartmentSurveyResponse.class);
        Root<ApartmentSurveyResponse> root = cq.from(ApartmentSurveyResponse.class);

        Predicate surveyIdPredicate = cb.equal(root.get("surveyId").get("id"), surveyId);
        cq.where(surveyIdPredicate);

        return s.createQuery(cq).getResultList();
    }

    @Override
    public void deleteSurveyResponseById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentSurveyResponse reponse = session.get(ApartmentSurveyResponse.class, id);
        session.delete(reponse);
    }

    @Override
    public void addSurveyResponse(ApartmentSurveyResponse response) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(response);
    }

    @Override
    public void updateSurveyResponse(ApartmentSurveyResponse response) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(response);
    }
}
