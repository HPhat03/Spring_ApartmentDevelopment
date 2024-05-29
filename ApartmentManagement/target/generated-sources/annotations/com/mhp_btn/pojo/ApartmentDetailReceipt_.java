package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentReceipt;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentDetailReceipt.class)
public class ApartmentDetailReceipt_ { 

    public static volatile SingularAttribute<ApartmentDetailReceipt, String> price;
    public static volatile SingularAttribute<ApartmentDetailReceipt, Integer> id;
    public static volatile SingularAttribute<ApartmentDetailReceipt, ApartmentReceipt> receiptId;
    public static volatile SingularAttribute<ApartmentDetailReceipt, String> content;

}