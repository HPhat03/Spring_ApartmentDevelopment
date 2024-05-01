package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentOtherMember;
import com.mhp_btn.pojo.ApartmentReceipt;
import com.mhp_btn.pojo.ApartmentRelativeRegistry;
import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.pojo.ApartmentResident;
import com.mhp_btn.pojo.ApartmentRoom;
import com.mhp_btn.pojo.ApartmentServiceConstract;
import com.mhp_btn.pojo.ApartmentSmartCabinet;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-01T20:34:35")
@StaticMetamodel(ApartmentRentalConstract.class)
public class ApartmentRentalConstract_ { 

    public static volatile SingularAttribute<ApartmentRentalConstract, Integer> finalPrice;
    public static volatile SingularAttribute<ApartmentRentalConstract, Short> isActive;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentSmartCabinet> apartmentSmartCabinetSet;
    public static volatile SingularAttribute<ApartmentRentalConstract, ApartmentRoom> roomId;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentReceipt> apartmentReceiptSet;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentRelativeRegistry> apartmentRelativeRegistrySet;
    public static volatile SingularAttribute<ApartmentRentalConstract, Date> createdDate;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentOtherMember> apartmentOtherMemberSet;
    public static volatile SingularAttribute<ApartmentRentalConstract, ApartmentResident> residentId;
    public static volatile SingularAttribute<ApartmentRentalConstract, Integer> id;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentReport> apartmentReportSet;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentServiceConstract> apartmentServiceConstractSet;
    public static volatile SetAttribute<ApartmentRentalConstract, ApartmentSurveyResponse> apartmentSurveyResponseSet;
    public static volatile SingularAttribute<ApartmentRentalConstract, String> status;

}