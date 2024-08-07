package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailReport;
import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.repositories.DetailRequestRepository;
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

@Transactional
@Repository
public class DetailRequestRepositoryImpl implements DetailRequestRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentDetailRequest> getAllDetailRequestByRequestID(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentDetailRequest> criteriaQuery = criteriaBuilder.createQuery(ApartmentDetailRequest.class);
        Root<ApartmentDetailRequest> root = criteriaQuery.from(ApartmentDetailRequest.class);

        Predicate condition = criteriaBuilder.equal(root.get("requestId"), id);

        criteriaQuery.select(root).where(condition);

        return s.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public ApartmentDetailRequest getDetailRequestById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("ApartmentDetailRequest.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentDetailRequest> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteDetailRequest(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentDetailRequest request = session.get(ApartmentDetailRequest.class, id);
        session.delete(request);

    }

    @Override
    public void addDetailRequest(ApartmentDetailRequest request) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(request);
    }

    @Override
    public void updateDetailRequest(ApartmentDetailRequest request) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(request);
    }
}
