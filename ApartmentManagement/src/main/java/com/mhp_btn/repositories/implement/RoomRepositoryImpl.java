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
import java.util.List;

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
    public void addRoom(ApartmentRoom room) {
        Session session = factoryBean.getObject().getCurrentSession();
        session.save(room);
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
    public List<ApartmentRoom> getEmptyRoom() {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentRoom.findByIsBlank");
        q.setParameter("isBlank", (short) 0);
        return q.getResultList();
    }

    @Override
    public void updateRoom(ApartmentRoom room) {
        Session session = this.factoryBean.getObject().getCurrentSession();
        session.update(room);
    }


}