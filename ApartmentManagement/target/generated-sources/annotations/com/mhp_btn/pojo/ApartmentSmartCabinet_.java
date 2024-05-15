package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-02T18:13:43")
@StaticMetamodel(ApartmentSmartCabinet.class)
public class ApartmentSmartCabinet_ { 

    public static volatile SingularAttribute<ApartmentSmartCabinet, String> decription;
    public static volatile SingularAttribute<ApartmentSmartCabinet, Date> createdDate;
    public static volatile SingularAttribute<ApartmentSmartCabinet, Integer> id;
    public static volatile SingularAttribute<ApartmentSmartCabinet, Date> updatedDate;
    public static volatile SingularAttribute<ApartmentSmartCabinet, ApartmentRentalConstract> apartmentId;
    public static volatile SingularAttribute<ApartmentSmartCabinet, String> status;

}