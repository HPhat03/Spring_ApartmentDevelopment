package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentRoom;

import java.util.List;
import java.util.Map;

public interface RoomRepository {
    List<ApartmentRoom> getRooms();
    List<ApartmentRoom> getRoomFilter(Map<String, String> params);
    void addOrUpdateRoom(ApartmentRoom room);
    public ApartmentRoom getRoomById(int id) ;
    void deleteRoomById(int id);



    List<ApartmentRoom> getRoomByFloorId(int id);

    void updateRoom(ApartmentRoom room);
    List<ApartmentRoom> getRoomsBlank ();
    public long countRoom() ;
}
