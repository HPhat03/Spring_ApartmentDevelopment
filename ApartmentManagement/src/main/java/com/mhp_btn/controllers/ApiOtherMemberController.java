package com.mhp_btn.controllers;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.services.DetailReceiptService;
import com.mhp_btn.services.OtherMemberService;
import com.mhp_btn.services.RentalConstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiOtherMemberController {
    @Autowired
    private OtherMemberService memberService;
    @Autowired
    private RentalConstractService apartmentService;

    // lay cac member theo id apartment
    @GetMapping(path="/apartment/{apartmentId}/members", produces = "application/json")
    public ResponseEntity<List<ApartmentOtherMember>> getOtherMembersByApartmentId(@PathVariable int apartmentId) {
        List<ApartmentOtherMember> members = memberService.getOtherMembersByApartmentId(apartmentId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
    //Xoa member theo id apartment id vaf memeber id
    @DeleteMapping(path = "/apartment/{apartmentId}/members/{memberId}")
    public ResponseEntity<?> deleteMemberByApartmentId(@PathVariable("apartmentId") int apartmentId,
                                                            @PathVariable("memberId") int memberId) {

        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        // Kiểm tra xem thành viên có tồn tại trong căn hộ không
        List<ApartmentOtherMember> members = memberService.getOtherMembersByApartmentId(apartmentId);
        boolean memberExists = false;
        for (ApartmentOtherMember member : members) {
            if (member.getId() == memberId) {
                memberExists = true;
                break;
            }
        }
        if (!memberExists) {
            return new ResponseEntity<>("Member not found with ID: " + memberId, HttpStatus.NOT_FOUND);
        }

        // Thực hiện xóa thành viên
        memberService.deleteMemberById(memberId);

        return new ResponseEntity<>("Member deleted successfully", HttpStatus.NO_CONTENT);
    }



    @PostMapping("/apartment/{apartmentId}/members")
    public ResponseEntity<?> createOtherMemberByApartmenrId(@PathVariable("apartmentId") int apartmentId, @RequestBody ApartmentOtherMember request) {
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }
        if (request.getName() == null || request.getRelationship() == null) {
            return new ResponseEntity<>("missing required information.", HttpStatus.BAD_REQUEST);
        }

        ApartmentOtherMember newMember = new ApartmentOtherMember();
        newMember.setApartmentId(apartment);
        newMember.setName(request.getName());
        newMember.setName(request.getRelationship());

        memberService.addOtherMemberByApartmentId(newMember);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value="/apartment/{apartmentId}/members/{memberId}" , produces = "application/json")
    public ResponseEntity<?> updateOtherMemberByMemberId(@PathVariable("apartmentId") int apartmentId,
                                                         @PathVariable("memberId") int memberId,
                                                         @RequestBody ApartmentOtherMember updateMember) {
        // Kiểm tra xem căn hộ có tồn tại không
        ApartmentRentalConstract apartment = apartmentService.getConstractById(apartmentId);
        if (apartment == null) {
            return new ResponseEntity<>("Apartment not found with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }

        // Kiểm tra xem thành viên có tồn tại trong căn hộ không
        List<ApartmentOtherMember> members = memberService.getOtherMembersByApartmentId(apartmentId);
        boolean memberExists = false;
        for (ApartmentOtherMember member : members) {
            if (member.getId() == memberId) {
                memberExists = true;
                break;
            }
        }
        if (!memberExists) {
            return new ResponseEntity<>("Member not found with ID: " + memberId + " in apartment with ID: " + apartmentId, HttpStatus.NOT_FOUND);
        }

        // Cập nhật thông tin thành viên
        updateMember.setId(memberId);
        updateMember.setApartmentId(apartment);
        updateMember.setRelationship(updateMember.getRelationship());
        memberService.updateOthMember(updateMember);

        return new ResponseEntity<>(updateMember, HttpStatus.OK);
    }


}
