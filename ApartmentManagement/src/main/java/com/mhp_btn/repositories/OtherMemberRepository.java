package com.mhp_btn.repositories;

import com.mhp_btn.pojo.ApartmentOtherMember;

import java.util.List;

public interface OtherMemberRepository {
    List<ApartmentOtherMember> getOtherMembersByApartmentId(int id);

    void deleteMemberById(int id);

    void addOtherMemberByApartmentId(ApartmentOtherMember member);

    void updateOthMember(ApartmentOtherMember member);
}
