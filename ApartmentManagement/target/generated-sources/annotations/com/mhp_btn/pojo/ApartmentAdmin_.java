package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentSurveyRequest;
import com.mhp_btn.pojo.ApartmentUser;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentAdmin.class)
public class ApartmentAdmin_ { 

    public static volatile SetAttribute<ApartmentAdmin, ApartmentSurveyRequest> apartmentSurveyRequestSet;
    public static volatile SingularAttribute<ApartmentAdmin, ApartmentUser> apartmentUser;
    public static volatile SingularAttribute<ApartmentAdmin, Integer> userId;

}