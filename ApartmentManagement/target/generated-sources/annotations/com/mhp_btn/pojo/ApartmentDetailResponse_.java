package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentDetailResponse.class)
public class ApartmentDetailResponse_ { 

    public static volatile SingularAttribute<ApartmentDetailResponse, ApartmentDetailRequest> questionId;
    public static volatile SingularAttribute<ApartmentDetailResponse, Integer> answer;
    public static volatile SingularAttribute<ApartmentDetailResponse, Integer> id;
    public static volatile SingularAttribute<ApartmentDetailResponse, ApartmentSurveyResponse> responseId;

}