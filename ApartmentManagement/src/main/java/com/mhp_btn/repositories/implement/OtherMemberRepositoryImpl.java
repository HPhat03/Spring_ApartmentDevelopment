package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.repositories.OtherMemberRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class OtherMemberRepositoryImpl implements OtherMemberRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public List<ApartmentOtherMember> getOtherMembersByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query<ApartmentOtherMember> query = session.createQuery(
                "SELECT m FROM ApartmentOtherMember m WHERE m.apartmentId.id = :apartmentId",
                ApartmentOtherMember.class);
        query.setParameter("apartmentId", id);
        return query.getResultList();
    }

    @Override
    public void deleteMemberById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentOtherMember member = session.get(ApartmentOtherMember.class, id);
        if (member != null) {
            session.delete(member);
        }
    }

    @Override
    public void addOtherMemberByApartmentId(ApartmentOtherMember member) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(member);
    }

    @Override
    public void updateOthMember(ApartmentOtherMember member) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(member);
    }

}
