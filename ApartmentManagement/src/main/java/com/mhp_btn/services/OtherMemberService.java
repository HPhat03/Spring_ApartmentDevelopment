package com.mhp_btn.services;

import com.mhp_btn.pojo.ApartmentOtherMember;

import java.util.List;

public interface OtherMemberService {
    List<ApartmentOtherMember> getOtherMembersByApartmentId(int id) ;

    public void deleteMemberById(int id) ;

    void addOtherMemberByApartmentId(ApartmentOtherMember member);

    void updateOthMember(ApartmentOtherMember member);

    ApartmentOtherMember getOtherMemberById(int id) ;

    public void deleteMembersByApartmentId(int apartmentId) ;
}
