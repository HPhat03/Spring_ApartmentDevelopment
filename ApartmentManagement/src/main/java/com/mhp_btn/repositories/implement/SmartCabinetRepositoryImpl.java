package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.repositories.SmartCabinetRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class SmartCabinetRepositoryImpl implements SmartCabinetRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentSmartCabinet> getAllSmartCabinets() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSmartCabinet.findAll");
        return q.getResultList();
    }

    @Override
    public List<ApartmentSmartCabinet> getAllSmartCabinetByApartmentId(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        org.hibernate.query.Query<ApartmentSmartCabinet> query = session.createQuery(
                "SELECT m FROM ApartmentSmartCabinet m WHERE m.apartmentId.id = :apartmentId",
                ApartmentSmartCabinet.class);
        query.setParameter("apartmentId", id);
        return query.getResultList();
    }

    @Override
    public ApartmentSmartCabinet getSmartCabinetById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentSmartCabinet.findById");
        q.setParameter("id", id); // Thiết lập giá trị của tham số ID
        List<ApartmentSmartCabinet> result = q.getResultList();
        return result.isEmpty() ? null :result.get(0);
    }

    @Override
    public void deleteCabinetById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        ApartmentSmartCabinet report = session.get(ApartmentSmartCabinet.class, id);
        session.delete(report);

    }

    @Override
    public void addCabinet(ApartmentSmartCabinet cabinet) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(cabinet);
    }

    @Override
    public void updateCabinet(ApartmentSmartCabinet cabinet) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(cabinet);
    }


}
