package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-02T00:33:15")
@StaticMetamodel(ApartmentRoom.class)
public class ApartmentRoom_ { 

    public static volatile SingularAttribute<ApartmentRoom, String> roomNumber;
    public static volatile SingularAttribute<ApartmentRoom, Date> createdDate;
    public static volatile SetAttribute<ApartmentRoom, ApartmentRentalConstract> apartmentRentalConstractSet;
    public static volatile SingularAttribute<ApartmentRoom, Integer> price;
    public static volatile SingularAttribute<ApartmentRoom, Short> isBlank;
    public static volatile SingularAttribute<ApartmentRoom, Integer> id;
    public static volatile SingularAttribute<ApartmentRoom, Short> isActive;
    public static volatile SingularAttribute<ApartmentRoom, ApartmentFloor> floor;

}