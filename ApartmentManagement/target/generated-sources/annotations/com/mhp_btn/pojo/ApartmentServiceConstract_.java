package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentService;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentServiceConstract.class)
public class ApartmentServiceConstract_ { 

    public static volatile SingularAttribute<ApartmentServiceConstract, Integer> id;
    public static volatile SingularAttribute<ApartmentServiceConstract, ApartmentService> serviceId;
    public static volatile SingularAttribute<ApartmentServiceConstract, ApartmentRentalConstract> apartmentId;

}