package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentServiceConstract;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-01T20:34:35")
@StaticMetamodel(ApartmentService.class)
public class ApartmentService_ { 

    public static volatile SingularAttribute<ApartmentService, Integer> price;
    public static volatile SingularAttribute<ApartmentService, String> name;
    public static volatile SingularAttribute<ApartmentService, Integer> id;
    public static volatile SingularAttribute<ApartmentService, Short> isActive;
    public static volatile SetAttribute<ApartmentService, ApartmentServiceConstract> apartmentServiceConstractSet;

}