package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-04-10T20:39:15")
@StaticMetamodel(ApartmentReceipt.class)
public class ApartmentReceipt_ { 

    public static volatile SingularAttribute<ApartmentReceipt, Integer> total;
    public static volatile SingularAttribute<ApartmentReceipt, Date> createdDate;
    public static volatile SingularAttribute<ApartmentReceipt, Integer> month;
    public static volatile SetAttribute<ApartmentReceipt, ApartmentDetailReceipt> apartmentDetailReceiptSet;
    public static volatile SingularAttribute<ApartmentReceipt, Integer> id;
    public static volatile SingularAttribute<ApartmentReceipt, ApartmentRentalConstract> apartmentId;

}