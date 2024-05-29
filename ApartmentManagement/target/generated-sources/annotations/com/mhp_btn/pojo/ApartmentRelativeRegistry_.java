package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentRelativeRegistry.class)
public class ApartmentRelativeRegistry_ { 

    public static volatile SingularAttribute<ApartmentRelativeRegistry, Date> endDate;
    public static volatile SingularAttribute<ApartmentRelativeRegistry, String> name;
    public static volatile SingularAttribute<ApartmentRelativeRegistry, Short> active;
    public static volatile SingularAttribute<ApartmentRelativeRegistry, Integer> id;
    public static volatile SingularAttribute<ApartmentRelativeRegistry, Date> startDate;
    public static volatile SingularAttribute<ApartmentRelativeRegistry, ApartmentRentalConstract> apartmentId;

}