package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.repositories.OtherMemberRepository;
import com.mhp_btn.repositories.implement.OtherMemberRepositoryImpl;
import com.mhp_btn.services.OtherMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherMemberServiceImpl implements OtherMemberService {

    @Autowired
    private OtherMemberRepository memberRepo;

    @Override
    public List<ApartmentOtherMember> getOtherMembersByApartmentId(int id) {
        return this.memberRepo.getOtherMembersByApartmentId(id);
    }

    @Override
    public void deleteMemberById(int id) {
        this.memberRepo.deleteMemberById(id);
    }

    @Override
    public void addOtherMemberByApartmentId(ApartmentOtherMember member) {
        this.memberRepo.addOtherMemberByApartmentId(member);
    }

    @Override
    public void updateOthMember(ApartmentOtherMember member) {
        this.memberRepo.updateOthMember(member);
    }
}
