package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentRentalConstract;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-02T18:13:43")
=======
@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-05T14:27:20")
>>>>>>> 8562484f7a349d165b5babbbbdd81b142e5c6cbc
@StaticMetamodel(ApartmentSurveyResponse.class)
public class ApartmentSurveyResponse_ { 

    public static volatile SingularAttribute<ApartmentSurveyResponse, ApartmentSurveyRequest> surveyId;
    public static volatile SingularAttribute<ApartmentSurveyResponse, Date> submitDate;
    public static volatile SingularAttribute<ApartmentSurveyResponse, String> name;
    public static volatile SetAttribute<ApartmentSurveyResponse, ApartmentDetailResponse> apartmentDetailResponseSet;
    public static volatile SingularAttribute<ApartmentSurveyResponse, Integer> id;
    public static volatile SingularAttribute<ApartmentSurveyResponse, ApartmentRentalConstract> apartmentId;

}