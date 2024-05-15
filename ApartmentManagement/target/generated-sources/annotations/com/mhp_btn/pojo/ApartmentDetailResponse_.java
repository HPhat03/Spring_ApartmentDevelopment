package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentDetailRequest;
import com.mhp_btn.pojo.ApartmentSurveyResponse;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-02T18:13:43")
@StaticMetamodel(ApartmentDetailResponse.class)
public class ApartmentDetailResponse_ { 

    public static volatile SingularAttribute<ApartmentDetailResponse, ApartmentDetailRequest> questionId;
    public static volatile SingularAttribute<ApartmentDetailResponse, Integer> answer;
    public static volatile SingularAttribute<ApartmentDetailResponse, Integer> id;
    public static volatile SingularAttribute<ApartmentDetailResponse, ApartmentSurveyResponse> responseId;

}