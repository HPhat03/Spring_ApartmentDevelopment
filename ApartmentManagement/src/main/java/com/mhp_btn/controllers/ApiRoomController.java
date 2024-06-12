package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.repositories.FloorRepository;
import com.mhp_btn.services.FloorService;
import com.mhp_btn.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rooms")
public class ApiRoomController {
    @Autowired
    private RoomService rs;
    @Autowired
    private FloorService floorSer;

    //    @RequestMapping("/")
//    public String index(Model model,
//                        @RequestParam Map<String, String> params) {
//
//        model.addAttribute("rooms", this.rs.getRoomFilter(params));
//        return "rooms";
//    }
    @GetMapping("/")
    public String Index(Model model) {
        model.addAttribute("rooms", this.rs.getRooms());
        return "rooms";
    }

    @GetMapping("/add")
    public String addRoom(Model model) {
        model.addAttribute("room", new ApartmentRoom());
        return "addRoom";
    }
    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable int id, Model model) {
        ApartmentRoom room = rs.getRoomById(id);
        model.addAttribute("room", room);
        return "addRoom";
    }

//    chưa bắt lỗi nhập liệu
    @PostMapping(path = "/add")
    public String addRoom(@ModelAttribute("room") ApartmentRoom room) {
        System.out.println("Room: " + room);
        room.setCreatedDate(new Date());

        this.rs.addOrUpdateRoom(room);

        return "redirect:/rooms/";

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