package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailReport;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentReport.class)
public class ApartmentReport_ { 

    public static volatile SetAttribute<ApartmentReport, ApartmentDetailReport> apartmentDetailReportSet;
    public static volatile SingularAttribute<ApartmentReport, Date> createdDate;
    public static volatile SingularAttribute<ApartmentReport, Integer> id;
    public static volatile SingularAttribute<ApartmentReport, String> title;
    public static volatile SingularAttribute<ApartmentReport, ApartmentRentalConstract> apartmentId;

}