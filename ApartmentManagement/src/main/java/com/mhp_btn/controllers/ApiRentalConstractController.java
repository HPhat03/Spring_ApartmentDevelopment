package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ResidentService;
import com.mhp_btn.services.RoomService;
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
public class ApiRentalConstractController {

    @Autowired
    private RentalConstractService constractService;
    @Autowired
    private ResidentService residentService;
    @Autowired
    private RoomService roomService;

    @GetMapping(path = "/constract/", produces = "application/json")
    public ResponseEntity<List<ApartmentRentalConstract>> list() {
        List<ApartmentRentalConstract> constract = this.constractService.getAllRentalConstract();
        return new ResponseEntity<>(constract, HttpStatus.OK);
    }

    @DeleteMapping("/constract/{id}")
    public ResponseEntity<String> deleteConstractById(@PathVariable int id) {

        ApartmentRentalConstract constract = constractService.getRentalConstractById(id);
        if (constract == null) {
            return new ResponseEntity<>("Không tìm thấy hợp đồng với ID " + id, HttpStatus.NOT_FOUND);
        }

        constractService.deleteRentalConstractById(id);
        return new ResponseEntity<>("Delete success with constract is ID " + id, HttpStatus.OK);
    }

    @PostMapping("/constract/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addConstract(@RequestBody Map<String, String> params) {
        try {
            // Lấy dữ liệu từ params
            int residentId = Integer.parseInt(params.get("resident_id"));
            int roomId = Integer.parseInt(params.get("room_id"));
            ApartmentResident resident = residentService.getResidentById(residentId);
            ApartmentRoom room = roomService.getRoomById(roomId);

            int isActive = Integer.parseInt(params.get("is_active"));
            int finalPrice = Integer.parseInt(params.get("final_price"));


            // Kiểm tra xem resident và room có tồn tại hay không
            if (resident != null && room != null) {

                ApartmentRentalConstract constract = new ApartmentRentalConstract();

                constract.setStatus("RENTED");
                constract.setIsActive((short) isActive);
                constract.setCreatedDate(new Date());
                constract.setFinalPrice(finalPrice);
                constract.setResidentId(resident);
                constract.setRoomId(room);

                constractService.addRentalConstract(constract);
            } else {
                throw new IllegalArgumentException("ERROR: Resident hoặc Room không tồn tại.");
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: Dữ liệu không hợp lệ.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @PatchMapping(value = "/constract/{id}", produces = "application/json")
    public ResponseEntity<ApartmentRentalConstract> updateConstractById(@PathVariable int id, @RequestBody Map<String, String> updates) {
        // Lấy thông tin
        ApartmentRentalConstract constract = constractService.getConstractById(id);

        if (constract == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "status":
                    constract.setStatus(value);
                    break;
                case "createdDate":
                    // Cần xử lý chuyển đổi từ string sang định dạng ngày tháng nếu cần
                    constract.setCreatedDate(new Date());
                    break;
                case "isActive":
                    // Cần chuyển đổi từ string sang kiểu boolean nếu cần
                    constract.setIsActive(Short.parseShort(value));
                    break;
                case "finalPrice":
                    // Cần chuyển đổi từ string sang kiểu số nếu cần
                    constract.setFinalPrice(Integer.parseInt(value));
                    break;
                case "roomId":

                        ApartmentRoom room = roomService.getRoomById(Integer.parseInt(value));
                        if (room != null) {
                            constract.setRoomId(room);
                        } else {
                            throw new IllegalArgumentException("Room with ID " + value + " not found");
                        }

                    break;
                case "residentId":
                        ApartmentResident resident = residentService.getResidentById(Integer.parseInt(value));
                        if (resident != null) {
                            constract.setResidentId(resident);
                        } else {
                            throw new IllegalArgumentException("Resident with ID " + value + " not found");
                        }

                    break;

            }
        }
        constractService.updateConstract(constract);
        return new ResponseEntity<>(constract, HttpStatus.OK);
    }


}



