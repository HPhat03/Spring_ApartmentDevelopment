package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentServiceConstract;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.serializers.RentalConstractSerializer;
import com.mhp_btn.serializers.ServiceSerializer;
import com.mhp_btn.services.OtherMemberService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ResidentService;
import com.mhp_btn.services.RoomService;
import com.mhp_btn.services.ServiceConstractService;
import com.mhp_btn.services.ServiceService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ServiceConstractService serviceConstractService;
    @Autowired
    private OtherMemberService otherMemberService;
    
    
    @GetMapping(path = "/constract/", produces = "application/json")
    public ResponseEntity<Object> list(@RequestParam HashMap<String,String> params) {
        List<ApartmentRentalConstract> constract = this.constractService.getAllRentalConstract(params);
        return new ResponseEntity<>(RentalConstractSerializer.RentalConstractList(constract), HttpStatus.OK);
    }
    
    @GetMapping(path = "/constract/{id}", produces = "application/json")
    public ResponseEntity<Object> retrieve(@PathVariable int id){
        ApartmentRentalConstract constract = constractService.getRentalConstractById(id);
        return ResponseEntity.ok(RentalConstractSerializer.RentalConstractDetail(constract));
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

    @PostMapping(path = "/constract/", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> addConstract(@RequestBody Map<String, Object> params) {
        try {
            // Lấy dữ liệu từ params
            int residentId = (int) params.get("resident_id");
            int roomId = (int) params.get("room_id");
            ApartmentResident resident = residentService.getResidentById(residentId);
            ApartmentRoom room = roomService.getRoomById(roomId);
            if(room == null )
                return new ResponseEntity<>("Không thể tìm thấy phòng", HttpStatus.BAD_REQUEST);
            
            int finalPrice = params.get("final_price")!=null ? (int) params.get("final_price") : room.getPrice();
            
            if (room.getIsBlank() != 1)
                return new ResponseEntity<>("Không thể cho thuê phòng đã có người thuê", HttpStatus.BAD_REQUEST);
            if (room.getIsActive() != 1)
                return new ResponseEntity<>("Không thể cho thuê phòng đã bị khóa", HttpStatus.BAD_REQUEST);

            if (resident == null || resident.getApartmentUser().getRole().equals(ApartmentUser.ADMIN)) {
                return new ResponseEntity<>("Không tìm thấy tài khoản người thuê", HttpStatus.BAD_REQUEST);
            }

            ApartmentRentalConstract constract = new ApartmentRentalConstract();

            constract.setStatus("RENTED");
            constract.setIsActive((short) 1);
            constract.setCreatedDate(new Date());
            constract.setFinalPrice(finalPrice);
            constract.setResidentId(resident);
            constract.setRoomId(room);

            constractService.addRentalConstract(constract);
            room.setIsBlank((short)0);
            roomService.updateRoom(room);
            
            //ADD SERVICES
            boolean addAll = true;
            int len = (int) serviceService.countService();
            List<Integer> services_id = new ArrayList<>();
            List<ApartmentService> services = new ArrayList<>();
            if (params.get("services")!= null){
                addAll = false;
                services_id =  (List<Integer>) params.get("services");
                len = services_id.size();
            }
//            else {
//                services = serviceService.getService(params);
//            }
            
            for(int i = 0; i<len; i++){
                ApartmentService temp;
                if(!addAll){
                    temp = serviceService.getServiceById(services_id.get(i));
                }
                else{
                    temp = services.get(i);
                }
                ApartmentServiceConstract sv = new ApartmentServiceConstract();
                sv.setServiceId(temp);
                sv.setApartmentId(constract);
                serviceConstractService.addServiceConstract(sv);
            }
            //ADD OTHER MEMBER
            if(params.get("other_members") != null){
                List<Map<String,String>> other_members = (List<Map<String,String>>) params.get("other_members");
                other_members.forEach(x -> System.out.println(x.get("name")));
                other_members.forEach(x -> {
                    System.out.println(x.get("name"));
                    ApartmentOtherMember temp = new ApartmentOtherMember();
                    temp.setApartmentId(constract);
                    temp.setName(x.get("name"));
                    temp.setRelationship(x.get("relationship"));
                    otherMemberService.addOtherMemberByApartmentId(temp);
                });
            }
            return new ResponseEntity<>(constract, HttpStatus.OK);


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



