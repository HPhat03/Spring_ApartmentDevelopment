package com.mhp_btn.repositories.implement;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.repositories.RoomRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<ApartmentRoom> getRooms() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findAll");
        return q.getResultList();
    }

    @Override
    public List<ApartmentRoom> getRoomFilter(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ApartmentRoom> q = b.createQuery(ApartmentRoom.class);
        Root<ApartmentRoom> r = q.from(ApartmentRoom.class);

        q.select(r);
        List<Predicate> predicates = new ArrayList<>();

//        String status = params.get("status");
//        System.out.println(status);
//        if (status != null && !status.equals("all")) {
//            predicates.add(b.like(r.get("status"), String.format("%%%s%%", status)));
//        }
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);

        List<ApartmentRoom> roomsFilter = query.getResultList();

        return roomsFilter;
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
        if (room != null) {
            // Cập nhật cờ isActive thành 0
            room.setIsActive((short) 0);
            s.update(room);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with id: " + id);
        }
    }

    @Override
    public List<ApartmentRoom> getActiveRooms() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findByIsActive");
        q.setParameter("isActive", (short) 1);
        return q.getResultList();
    }

    @Override
    public List<ApartmentRoom> getInactiveRooms() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findByIsActive");
        q.setParameter("isActive", (short) 0);
        return q.getResultList();
    }

    @Override
    public List<ApartmentRoom> getEmptyRoom() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findByIsBlank");
        q.setParameter("isBlank", (short) 0);
        return q.getResultList();
    }

    @Override
    public List<ApartmentRoom> getRentedRoom() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findByIsBlank");
        q.setParameter("isBlank", (short) 1);
        return q.getResultList();
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


}