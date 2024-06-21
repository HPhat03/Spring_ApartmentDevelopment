package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentRoom;

import java.util.List;
import java.util.Map;

public interface RoomService {
    List<ApartmentRoom> getRooms();
    void addOrUpdateRoom(ApartmentRoom room);
    public List<ApartmentRoom> getRoomFilter(Map<String, String> params) ;
    public ApartmentRoom getRoomById(int id) ;
    public void deleteRoomById(int id);


    List<ApartmentRoom> getRoomByFloorId(int id) ;

    void updateRoom(ApartmentRoom room);
    public List<ApartmentRoom> getRoomsBlank() ;
    public long countRoom() ;
}
