package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-01T20:34:35")
@StaticMetamodel(ApartmentResident.class)
public class ApartmentResident_ { 

    public static volatile SetAttribute<ApartmentResident, ApartmentRentalConstract> apartmentRentalConstractSet;
    public static volatile SingularAttribute<ApartmentResident, ApartmentUser> apartmentUser;
    public static volatile SingularAttribute<ApartmentResident, Integer> userId;
    public static volatile SingularAttribute<ApartmentResident, Date> joinedDate;

}