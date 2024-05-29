package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailReceipt;
import com.mhp_btn.pojo.ApartmentPayment;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentReceipt.class)
public class ApartmentReceipt_ { 

    public static volatile SingularAttribute<ApartmentReceipt, Integer> total;
    public static volatile SingularAttribute<ApartmentReceipt, Date> createdDate;
    public static volatile SingularAttribute<ApartmentReceipt, Integer> month;
    public static volatile SetAttribute<ApartmentReceipt, ApartmentPayment> apartmentPaymentSet;
    public static volatile SingularAttribute<ApartmentReceipt, String> year;
    public static volatile SetAttribute<ApartmentReceipt, ApartmentDetailReceipt> apartmentDetailReceiptSet;
    public static volatile SingularAttribute<ApartmentReceipt, Integer> id;
    public static volatile SingularAttribute<ApartmentReceipt, ApartmentRentalConstract> apartmentId;

}