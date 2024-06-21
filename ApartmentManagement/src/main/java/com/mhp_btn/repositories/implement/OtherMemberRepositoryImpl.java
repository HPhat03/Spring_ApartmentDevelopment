package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.repositories.OtherMemberRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class OtherMemberRepositoryImpl implements OtherMemberRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Override
    public List<ApartmentOtherMember> getOtherMembersByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ApartmentOtherMember> cq = cb.createQuery(ApartmentOtherMember.class);
        Root<ApartmentOtherMember> root = cq.from(ApartmentOtherMember.class);

        cq.select(root)
                .where(cb.equal(root.get("apartmentId").get("id"), id));

        Query<ApartmentOtherMember> query = session.createQuery(cq);
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

    @Override
    public ApartmentOtherMember getOtherMemberById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createNamedQuery("ApartmentOtherMember.findById");
        q.setParameter("id", id); // Đặt tên tham số là 'userId'
        List<ApartmentOtherMember> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public void deleteMembersByApartmentId(int apartmentId) {

        Session session = factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<ApartmentOtherMember> cd = cb.createCriteriaDelete(ApartmentOtherMember.class);
        Root<ApartmentOtherMember> root = cd.from(ApartmentOtherMember.class);

        cd.where(cb.equal(root.get("apartmentId").get("id"), apartmentId));

        Query query = session.createQuery(cd);
        query.executeUpdate();
    }

}
