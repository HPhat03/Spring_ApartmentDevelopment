package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentReport;
import com.mhp_btn.pojo.ApartmentReportPicture;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-04-30T15:43:19")
@StaticMetamodel(ApartmentDetailReport.class)
public class ApartmentDetailReport_ { 

    public static volatile SingularAttribute<ApartmentDetailReport, ApartmentReport> reportId;
    public static volatile SetAttribute<ApartmentDetailReport, ApartmentReportPicture> apartmentReportPictureSet;
    public static volatile SingularAttribute<ApartmentDetailReport, Integer> id;
    public static volatile SingularAttribute<ApartmentDetailReport, String> content;

}