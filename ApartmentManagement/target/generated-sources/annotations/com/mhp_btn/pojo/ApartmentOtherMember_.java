package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentOtherMember.class)
public class ApartmentOtherMember_ { 

    public static volatile SingularAttribute<ApartmentOtherMember, String> name;
    public static volatile SingularAttribute<ApartmentOtherMember, Integer> id;
    public static volatile SingularAttribute<ApartmentOtherMember, String> relationship;
    public static volatile SingularAttribute<ApartmentOtherMember, ApartmentRentalConstract> apartmentId;

}