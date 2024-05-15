package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.repositories.DetailResponseRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class DetailResponseRepositoryImpl implements DetailResponseRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentDetailResponse> getAllDetailResponseByResponseID(int id) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        Query query = session.createQuery("FROM ApartmentDetailResponse adr WHERE adr.responseId.id = :responseId");
        query.setParameter("responseId", id);
        return query.getResultList();
    }

    @Override
    public ApartmentDetailResponse getDetailResponseById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("ApartmentDetailResponse.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentDetailResponse> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteDetailResponse(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentDetailResponse r = session.get(ApartmentDetailResponse.class, id);
        session.delete(r);
    }

    @Override
    public void addDetailResponse(ApartmentDetailResponse response) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(response);
    }

    @Override
    public void updateDetailResponse(ApartmentDetailResponse response) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(response);
    }
}
