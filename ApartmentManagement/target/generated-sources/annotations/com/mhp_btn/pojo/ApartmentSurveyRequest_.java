package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-04-30T00:22:12")
@StaticMetamodel(ApartmentSurveyRequest.class)
public class ApartmentSurveyRequest_ { 

    public static volatile SingularAttribute<ApartmentSurveyRequest, Date> endDate;
    public static volatile SetAttribute<ApartmentSurveyRequest, ApartmentDetailRequest> apartmentDetailRequestSet;
    public static volatile SingularAttribute<ApartmentSurveyRequest, ApartmentAdmin> adminId;
    public static volatile SingularAttribute<ApartmentSurveyRequest, Integer> id;
    public static volatile SingularAttribute<ApartmentSurveyRequest, Short> isActive;
    public static volatile SetAttribute<ApartmentSurveyRequest, ApartmentSurveyResponse> apartmentSurveyResponseSet;
    public static volatile SingularAttribute<ApartmentSurveyRequest, Date> startDate;

}