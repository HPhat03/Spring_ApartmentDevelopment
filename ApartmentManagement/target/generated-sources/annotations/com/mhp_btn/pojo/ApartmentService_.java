package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentServiceConstract;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentService.class)
public class ApartmentService_ { 

    public static volatile SingularAttribute<ApartmentService, Integer> price;
    public static volatile SingularAttribute<ApartmentService, String> name;
    public static volatile SingularAttribute<ApartmentService, Integer> id;
    public static volatile SingularAttribute<ApartmentService, Short> isActive;
    public static volatile SetAttribute<ApartmentService, ApartmentServiceConstract> apartmentServiceConstractSet;

}