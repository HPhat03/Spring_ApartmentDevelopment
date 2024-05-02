package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentResident;
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

    @PatchMapping(value = "/room/{id}", produces = "application/json")
    public ResponseEntity<ApartmentRoom> updateRoomById(@PathVariable int id, @RequestBody Map<String, String> updates) {
        ApartmentRoom room = rs.getRoomById(id);
        if (room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Cập nhật thông tin phòng nếu có
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "roomNumber":
                    room.setRoomNumber(value);
                    break;
                case "isBlank":
                    room.setIsBlank(Short.parseShort(value));
                    break;
                case "price":
                    room.setPrice(Integer.parseInt(value));
                    break;
                case "isActive":
                    room.setIsActive(Short.parseShort(value));
                    break;
                case "floor":
                    ApartmentFloor floor = floorSer.getFloorById(Integer.parseInt(value));
                    if (floor != null) {
                        room.setFloor(floor);
                    } else {
                        throw new IllegalArgumentException("Floor with ID " + value + " not found");
                    }

                    break;
                default:
                    // Nếu trường không hợp lệ, ném ra ngoại lệ hoặc trả về lỗi 400 Bad Request
                    throw new IllegalArgumentException("Trường " + key + " không hợp lệ");
            }
        }

        // Lưu cập nhật vào cơ sở dữ liệu
        rs.updateRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

}