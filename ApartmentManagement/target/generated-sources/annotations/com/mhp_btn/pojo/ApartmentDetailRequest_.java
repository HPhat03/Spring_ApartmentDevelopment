package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-04-30T15:43:19")
@StaticMetamodel(ApartmentDetailRequest.class)
public class ApartmentDetailRequest_ { 

    public static volatile SingularAttribute<ApartmentDetailRequest, String> question;
    public static volatile SingularAttribute<ApartmentDetailRequest, ApartmentSurveyRequest> requestId;
    public static volatile SingularAttribute<ApartmentDetailRequest, String> scoreBand;
    public static volatile SetAttribute<ApartmentDetailRequest, ApartmentDetailResponse> apartmentDetailResponseSet;
    public static volatile SingularAttribute<ApartmentDetailRequest, Integer> id;

}