package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-02T18:13:43")
=======
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-05T04:20:31")
>>>>>>> 8562484f7a349d165b5babbbbdd81b142e5c6cbc
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