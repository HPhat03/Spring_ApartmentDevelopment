package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentPaidPicture;
import com.mhp_btn.pojo.ApartmentReceipt;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentPayment.class)
public class ApartmentPayment_ { 

    public static volatile SetAttribute<ApartmentPayment, ApartmentPaidPicture> apartmentPaidPictureSet;
    public static volatile SingularAttribute<ApartmentPayment, Date> createdDate;
    public static volatile SingularAttribute<ApartmentPayment, String> paymentMethod;
    public static volatile SingularAttribute<ApartmentPayment, ApartmentReceipt> receipt;
    public static volatile SingularAttribute<ApartmentPayment, Integer> id;

}