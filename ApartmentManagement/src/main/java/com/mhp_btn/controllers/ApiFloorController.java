package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.services.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiFloorController {
    @Autowired
    private FloorService floorService;
    @GetMapping(path = "/floors/", produces = "application/json")
    public ResponseEntity<List<ApartmentFloor>> list() {
        List<ApartmentFloor> floors = this.floorService.getAllFloor();
        return new ResponseEntity<>(floors, HttpStatus.OK);
    }

    @PostMapping("/floors/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFloor(@RequestParam("name") String floorName) {
        if (floorName == null || floorName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tên tầng là bắt buộc");
        }

        ApartmentFloor newFloor = new ApartmentFloor();
        newFloor.setName(floorName);
        this.floorService.addFloor(newFloor);
    }

    @DeleteMapping("/floors/delete/{id}")
    public ResponseEntity<?> deleteFloorById(@PathVariable int id) {
        // Kiểm tra xem tầng có tồn tại không trước khi xóa
        ApartmentFloor floor = floorService.getFloorById(id);
        if (floor == null) {
            return new ResponseEntity<>("Không tìm thấy tầng với ID " + id, HttpStatus.NOT_FOUND);
        }

        // Nếu tầng tồn tại, thực hiện xóa
        floorService.deleteFloorById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/floors/{id}", produces = "application/json")
    public ResponseEntity<ApartmentFloor> updateFloorById(@PathVariable int id, @RequestBody Map<String, String> updates) {
        // Lấy thông tin tầng từ ID
        ApartmentFloor floor = floorService.getFloorById(id);

        if (floor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Cập nhật thông tin tầng nếu có
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("name")) {
                floor.setName(value);
            }
        }

        floorService.updateFloor(floor);

        return new ResponseEntity<>(floor, HttpStatus.OK);
    }


}
