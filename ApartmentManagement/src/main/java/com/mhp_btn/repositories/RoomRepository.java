package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentRoom;

import java.util.List;

public interface RoomRepository {
    List<ApartmentRoom> getRooms();
    void addRoom(ApartmentRoom room);
    public ApartmentRoom getRoomById(int id) ;
    void deleteRoomById(int id);

    List<ApartmentRoom> getActiveRooms();
    List<ApartmentRoom> getEmptyRoom();

    void updateRoom(ApartmentRoom room);

}
