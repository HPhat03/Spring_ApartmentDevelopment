package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.repositories.FloorRepository;
import com.mhp_btn.services.FloorService;
import com.mhp_btn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiRoomController {
    @Autowired
    private RoomService rs;
    @Autowired
    private FloorService floorSer;

    @GetMapping(path = "/room/", produces = "application/json")
    public ResponseEntity<List<ApartmentRoom>> list() {
        List<ApartmentRoom> activeRooms = this.rs.getActiveRooms();
        return new ResponseEntity<>(activeRooms, HttpStatus.OK);
    }
    // Endpoint để lấy danh sách các phòng trống
    @GetMapping(path = "/room/empty", produces = "application/json")
    public ResponseEntity<List<ApartmentRoom>> listEmptyRooms() {
        List<ApartmentRoom> emptyRooms = this.rs.getEmptyRoom();
        return new ResponseEntity<>(emptyRooms, HttpStatus.OK);
    }

    @PostMapping("/room/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoom(@RequestBody Map<String, String> params) {

        int floorId = Integer.parseInt(params.get("floor"));
        ApartmentFloor floor = floorSer.getFloorById(floorId);

        // Kiểm tra xem tầng có tồn tại không
        if (floor != null) {
            ApartmentRoom room = new ApartmentRoom();
            room.setRoomNumber(params.get("roomNumber"));
            room.setIsBlank((short) 1);
            room.setPrice(Integer.parseInt(params.get("price")));
            room.setIsActive((short) 1);
            room.setFloor(floor);

            Date currentDate = new Date();
            room.setCreatedDate(currentDate);

            rs.addRoom(room);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy tầng với ID " + floorId);

        }
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable int id) {
        // Kiểm tra xem phòng có tồn tại không trước khi xóa
        ApartmentRoom room = rs.getRoomById(id);
        if (room == null) {
            return new ResponseEntity<>("Không tìm thấy phòng với ID " + id, HttpStatus.NOT_FOUND);
        }

        // Nếu phòng tồn tại, thực hiện xóa
        rs.deleteRoomById(id);
        return new ResponseEntity<>("Đã xóa phòng có ID " + id, HttpStatus.OK);
    }
}