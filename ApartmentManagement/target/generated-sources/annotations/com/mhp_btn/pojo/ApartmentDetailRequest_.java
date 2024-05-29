package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailResponse;
import com.mhp_btn.pojo.ApartmentSurveyRequest;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentDetailRequest.class)
public class ApartmentDetailRequest_ { 

    public static volatile SingularAttribute<ApartmentDetailRequest, String> question;
    public static volatile SingularAttribute<ApartmentDetailRequest, ApartmentSurveyRequest> requestId;
    public static volatile SingularAttribute<ApartmentDetailRequest, String> scoreBand;
    public static volatile SetAttribute<ApartmentDetailRequest, ApartmentDetailResponse> apartmentDetailResponseSet;
    public static volatile SingularAttribute<ApartmentDetailRequest, Integer> id;

}