package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentRoom;

import java.util.List;

public interface RoomService {
    List<ApartmentRoom> getRooms();
    void addRoom(ApartmentRoom room);

    public ApartmentRoom getRoomById(int id) ;
    public void deleteRoomById(int id);

    List<ApartmentRoom> getActiveRooms();
    List<ApartmentRoom> getEmptyRoom();

    void updateRoom(ApartmentRoom room);
}
