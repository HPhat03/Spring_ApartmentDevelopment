package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentFloor;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-02T18:13:43")
=======
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-05T00:00:47")
>>>>>>> 8562484f7a349d165b5babbbbdd81b142e5c6cbc
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