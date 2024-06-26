/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReportPicture;
import com.mhp_btn.repositories.ReportPictureRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ReportPictureRepositoryImpl implements ReportPictureRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public void addOrUpdateReportPicture(ApartmentReportPicture p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p.getId()==null)
            s.save(p);
        else
            s.update(p);
    }

    @Override
    public List<ApartmentReportPicture> getPicturesByReportId(int reportId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentReportPicture> cq = cb.createQuery(ApartmentReportPicture.class);
        Root<ApartmentReportPicture> root = cq.from(ApartmentReportPicture.class);

        Predicate reportIdPredicate = cb.equal(root.get("reportId").get("reportId").get("id"), reportId);
        cq.where(reportIdPredicate);
        return s.createQuery(cq).getResultList();
    }

}
