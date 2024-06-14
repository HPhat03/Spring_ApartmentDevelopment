package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRoomController {
    @Autowired
    private RoomService rs;
    @DeleteMapping("/rooms/{id}")
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
