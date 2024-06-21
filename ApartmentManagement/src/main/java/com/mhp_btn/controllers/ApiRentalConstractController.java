package com.mhp_btn.controllers;

import com.google.common.collect.HashBiMap;
import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentService;
import com.mhp_btn.pojo.ApartmentServiceConstract;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.serializers.ReceiptSerializer;
import com.mhp_btn.serializers.RentalConstractSerializer;
import com.mhp_btn.serializers.ReportSerializer;
import com.mhp_btn.serializers.ServiceSerializer;
import com.mhp_btn.services.OtherMemberService;
import com.mhp_btn.services.ReceiptService;
import com.mhp_btn.services.RelativeRegistryService;
import com.mhp_btn.services.RentalConstractService;
import com.mhp_btn.services.ReportService;
import com.mhp_btn.services.ResidentService;
import com.mhp_btn.services.RoomService;
import com.mhp_btn.services.ServiceConstractService;
import com.mhp_btn.services.ServiceService;
import com.mhp_btn.services.SmartCabinetService;
import java.security.Principal;

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
import javax.ws.rs.PathParam;
import org.springframework.http.MediaType;


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
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private RelativeRegistryService RRService;
    @Autowired
    private SmartCabinetService SCService;
    @Autowired
    private ReportService reportService;
    
    @GetMapping(path = "/my_constract/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> myConstract(Principal p)
    {
        HashMap<String, String> filter = new HashMap<>();
        filter.put("resident", p.getName());
        List<ApartmentRentalConstract> constract = this.constractService.getAllRentalConstract(filter);
        return new ResponseEntity<>(RentalConstractSerializer.RentalConstractAuthList(constract), HttpStatus.OK);
    }
    @GetMapping(path = "/constract/", produces = "application/json")
    public ResponseEntity<Object> list(@RequestParam HashMap<String,String> params) {
        List<ApartmentRentalConstract> constract = this.constractService.getAllRentalConstract(params);
        return new ResponseEntity<>(RentalConstractSerializer.RentalConstractList(constract), HttpStatus.OK);
    }
    
    @GetMapping(path = "/constract/{id}/", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<Object> retrieve(@PathVariable int id, Principal p){
        if(!this.constractService.checkRenter(id, p.getName()))
            return new ResponseEntity<>("Không thể xem hợp đồng của người khác", HttpStatus.BAD_REQUEST);
        ApartmentRentalConstract constract = constractService.getRentalConstractById(id);
        
        return ResponseEntity.ok(RentalConstractSerializer.RentalConstractDetail(constract));
    }

    @DeleteMapping("/constracts/{id}/")
    public ResponseEntity<String> deleteConstractById(@PathVariable int id) {

        // Lấy hợp đồng bởi ID
        ApartmentRentalConstract constract = constractService.getRentalConstractById(id);
        if (constract == null) {
            return new ResponseEntity<>("Không tìm thấy hợp đồng với ID " + id, HttpStatus.NOT_FOUND);
        }

        try {
            // Lấy ID của căn hộ từ hợp đồng
            Integer apartmentId = constract.getId();
            // Cập nhật trạng thái phòng
            ApartmentRoom room = constract.getRoomId();
            if (room != null) {
                room.setIsBlank((short) 1);
                roomService.addOrUpdateRoom(room);
            }

            // Xóa các other members theo apartment ID
            List<ApartmentOtherMember> members = otherMemberService.getOtherMembersByApartmentId(apartmentId);
            if (members.size() > 0) {
                otherMemberService.deleteMembersByApartmentId(apartmentId);
            }

            // Xóa các hợp đồng service theo apartment ID
            List<ApartmentService> servicesCon = scs.getServicesByApartmentId(apartmentId);
            if (servicesCon.size() > 0) {
                scs.deleteServiceConstractByApartmentId(apartmentId);
            }

            constractService.deleteRentalConstractById(id);
            return new ResponseEntity<>("Đã xóa hợp đồng thành công", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi xóa hợp đồng: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/constract/{id}/receipts/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Object> getReceiptOfConstract(@PathVariable int id, @RequestParam HashMap<String, String> params, Principal p) {
        params.put("apartment", String.format("%d", id));
        if(!this.constractService.checkRenter(id, p.getName()))
            return new ResponseEntity<>("Không thể xem của người khác", HttpStatus.OK);
        List<ApartmentReceipt> receipts = this.receiptService.getAllReceipt(params);
        return new ResponseEntity<>(ReceiptSerializer.ReceiptList(receipts), HttpStatus.OK);
    }
    
    @GetMapping(path = "/constract/{id}/relative_registry/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> getRelativeRegistryOfConstract(@PathVariable int id, @RequestParam HashMap<String, String> params, Principal p){
        if(!this.constractService.checkRenter(id, p.getName()))
            return new ResponseEntity<>("Không thể xem của người khác", HttpStatus.OK);
        return ResponseEntity.ok(this.RRService.getRRByApartmentId(id, params));
    }
    
    @GetMapping(path = "/constract/{id}/smart_cabinets/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> getCabinetOfConstract(@PathVariable int id, Principal p){
        if(!this.constractService.checkRenter(id, p.getName()))
            return new ResponseEntity<>("Không thể xem của người khác", HttpStatus.OK);
        return ResponseEntity.ok(this.SCService.getAllSmartCabinetByApartmentId(id));
    }
    
    @GetMapping(path = "/constract/{id}/reports/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<?> getReportsOfConstract(@PathVariable int id, @PathParam(value = "page") int page, Principal p){
        if(!this.constractService.checkRenter(id, p.getName()))
            return new ResponseEntity<>("Không thể xem của người khác", HttpStatus.OK);
        return ResponseEntity.ok(ReportSerializer.ReportList(this.reportService.getAllReportByApartmentId(id, page)));
    }
    
    @PostMapping(path = "/constract/", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> addConstract(@RequestBody Map<String, Object> params) {
        try {
            // Lấy dữ liệu từ params
            int residentId = (int) params.get("resident_id");
            int roomId = (int) params.get("room_id");
            ApartmentResident resident = residentService.getResidentById(residentId);
            ApartmentRoom room = roomService.getRoomById(roomId);
            if (room == null)
                return new ResponseEntity<>("Không thể tìm thấy phòng", HttpStatus.BAD_REQUEST);

            int finalPrice = params.get("final_price") != null ? (int) params.get("final_price") : room.getPrice();

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
            room.setIsBlank((short) 0);
            roomService.updateRoom(room);

            //ADD SERVICES
            boolean addAll = true;
            int len = (int) serviceService.countService();
            List<Integer> services_id = new ArrayList<>();
            List<ApartmentService> services = new ArrayList<>();
            if (params.get("services") != null) {
                addAll = false;
                services_id = (List<Integer>) params.get("services");
                len = services_id.size();
            } else {
                services = serviceService.getAllServices();

            }

            for (int i = 0; i < len; i++) {
                ApartmentService temp;
                if (!addAll) {
                    temp = serviceService.getServiceById(services_id.get(i));
                } else {
                    temp = services.get(i);
                }

                ApartmentServiceConstract sv = new ApartmentServiceConstract();
                sv.setServiceId(temp);
                sv.setApartmentId(constract);
                serviceConstractService.addServiceConstract(sv);
            }
            //ADD OTHER MEMBER
            if (params.get("other_members") != null) {
                List<Map<String, String>> other_members = (List<Map<String, String>>) params.get("other_members");
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
            return new ResponseEntity<>("Tạo thành công", HttpStatus.OK);


        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ERROR: Dữ liệu không hợp lệ.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PatchMapping(value = "/constract/edit/{id}/", produces = "application/json")
    public ResponseEntity<Object> updateConstractById(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        // Lấy thông tin hợp đồng
        ApartmentRentalConstract constract = constractService.getConstractById(id);

        if (constract == null) {
            return new ResponseEntity<>("Không tìm thấy hợp đồng với ID " + id, HttpStatus.NOT_FOUND);
        }

        try {
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                switch (key) {
                    case "status":
                        constract.setStatus((String) value);
                        break;
                    case "isActive":
                        constract.setIsActive(Short.parseShort(value.toString()));
                        break;
                    case "finalPrice":
                        constract.setFinalPrice(Integer.parseInt(value.toString()));
                        break;
                    case "services":
                        List<Integer> services_id = (List<Integer>) value;
                        List<ApartmentService> existingServices = scs.getServicesByApartmentId(constract.getId());

                        // Xóa các dịch vụ không còn trong danh sách mới
                        existingServices.forEach(s -> {
                            if (!services_id.contains(s.getId())) {
                                scs.deleteServiceConstractById(s.getId());
                            }
                        });

                        // Thêm các dịch vụ mới
                        services_id.forEach(sid -> {
                            boolean exists = existingServices.stream().anyMatch(es -> es.getId().equals(sid));
                            if (!exists) {
                                ApartmentService service = serviceService.getServiceById(sid);
                                ApartmentServiceConstract serviceConstract = new ApartmentServiceConstract();
                                serviceConstract.setServiceId(service);
                                serviceConstract.setApartmentId(constract);
                                scs.addServiceConstract(serviceConstract);
                            }
                        });
                        break;
                    case "other_members":
                        List<Map<String, String>> other_members = (List<Map<String, String>>) value;
                        List<ApartmentOtherMember> existingMembers = otherMemberService.getOtherMembersByApartmentId(constract.getId());

                        // Xóa các thành viên khác cũ
                        existingMembers.forEach(member -> otherMemberService.deleteMemberById(member.getId()));

                        // Thêm các thành viên khác mới
                        other_members.forEach(memberData -> {
                            ApartmentOtherMember member = new ApartmentOtherMember();
                            member.setApartmentId(constract);
                            member.setName(memberData.get("name"));
                            member.setRelationship(memberData.get("relationship"));
                            otherMemberService.addOtherMemberByApartmentId(member);
                        });
                        break;
                    default:
                        break;
                }
            }

            constractService.updateConstract(constract);
            return new ResponseEntity<>(RentalConstractSerializer.RentalConstractDetail(constract), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Dữ liệu không hợp lệ: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi cập nhật hợp đồng: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}



