package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.repositories.RoomRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;
    @Autowired
    private Environment env;
    @Override
    public List<ApartmentRoom> getRooms() {
        Session s = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findAll");
        return q.getResultList();
    }

    @Override
    public List<ApartmentRoom> getRoomFilter(Map<String, String> params) {
        Session s = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentRoom> q = b.createQuery(ApartmentRoom.class);
        Root<ApartmentRoom> r = q.from(ApartmentRoom.class);

        q.select(r);
        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("status")) {
            String status = params.get("status");
            switch (status) {
                case "active":
                    predicates.add(b.equal(r.get("isActive"), 1));
                    break;
                case "inactive":
                    predicates.add(b.equal(r.get("isActive"), 0));
                    break;
                case "blank":
                    predicates.add(b.equal(r.get("isBlank"), 0));
                    break;
                case "iblank":
                    predicates.add(b.equal(r.get("isBlank"), 1));
                    break;
                default:
                    break;
            }
        }

        q.where(predicates.toArray(new Predicate[0]));

        Query query = s.createQuery(q);
        //phan trang
        String page = params.get("page");
        if(page != null && !page.isEmpty() ){
            int pagesize = Integer.parseInt(Objects.requireNonNull(env.getProperty("room.pagesize")));
            int start = (Integer.parseInt(page)-1) * pagesize;
            query.setFirstResult(start);
            query.setMaxResults(pagesize);
        }
        return (List<ApartmentRoom>) query.getResultList();
    }



    @Override
    public void addOrUpdateRoom(ApartmentRoom room) {
        Session s = factoryBean.getObject().getCurrentSession();
       s.saveOrUpdate(room);
    }

    @Override
    public ApartmentRoom getRoomById(int id) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findById");
        q.setParameter("id", id); // Thiết lập giá trị của tham số ID
        List<ApartmentRoom> resultList = q.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }


    @Override
    public void deleteRoomById(int id) {
        Session s = factoryBean.getObject().getCurrentSession();
        ApartmentRoom room = s.get(ApartmentRoom.class, id);
        if(room!=null){
            s.delete(room);
        }

    }



    @Override
    public List<ApartmentRoom> getRoomByFloorId(int floorId) {
        Session session = factoryBean.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT r FROM ApartmentRoom r WHERE r.floor.id = :floorId");
        query.setParameter("floorId", floorId);
        return query.getResultList();
    }


    @Override
    public void updateRoom(ApartmentRoom room) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(room);
    }
    @Override
    public List<ApartmentRoom> getRoomsBlank() {
        Session s = Objects.requireNonNull(this.factoryBean.getObject()).getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findByIsBlank", ApartmentRoom.class);
        q.setParameter("isBlank", (short) 1);
        return q.getResultList();
    }


    @Override
    public long countRoom() {
        Session session = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        q.select(cb.count(q.from(ApartmentRoom.class)));
        Query rq = session.createQuery(q);
        return (long) rq.getSingleResult();
    }


}