package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.repositories.RoomRepository;
import com.mhp_btn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepo;
    @Override
    public List<ApartmentRoom> getRooms() {
        return this.roomRepo.getRooms();
    }

    @Override
    public void addOrUpdateRoom(ApartmentRoom room) {
        this.roomRepo.addOrUpdateRoom(room);
    }

    @Override
    public List<ApartmentRoom> getRoomFilter(Map<String, String> params) {
        return roomRepo.getRoomFilter(params);
    }

    @Override
    public ApartmentRoom getRoomById(int id) {
        return  this.roomRepo.getRoomById(id);
    }

    @Override
    public void deleteRoomById(int id) {
        this.roomRepo.deleteRoomById(id);
    }

    @Override
    public List<ApartmentRoom> getActiveRooms() {
        return this.roomRepo.getActiveRooms();
    }

    @Override
    public List<ApartmentRoom> getEmptyRoom() {
        return this.roomRepo.getEmptyRoom();
    }

    @Override
    public List<ApartmentRoom> getRentedRoom() {
        return this.roomRepo.getRentedRoom();
    }

    @Override
    public List<ApartmentRoom> getInactiveRooms() {
        return this.roomRepo.getInactiveRooms();
    }

    @Override
    public List<ApartmentRoom> getRoomByFloorId(int id) {
        return this.roomRepo.getRoomByFloorId(id);
    }

    @Override
    public void updateRoom(ApartmentRoom room) {
        this.roomRepo.updateRoom(room);
    }


}