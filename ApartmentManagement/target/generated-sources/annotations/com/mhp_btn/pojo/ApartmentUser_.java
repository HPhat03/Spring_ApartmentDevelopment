package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentResident;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-05-20T14:47:14", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(ApartmentUser.class)
public class ApartmentUser_ { 

    public static volatile SingularAttribute<ApartmentUser, String> lastName;
    public static volatile SingularAttribute<ApartmentUser, Date> birthdate;
    public static volatile SingularAttribute<ApartmentUser, String> role;
    public static volatile SingularAttribute<ApartmentUser, Short> gender;
    public static volatile SingularAttribute<ApartmentUser, String> avatar;
    public static volatile SingularAttribute<ApartmentUser, Short> isActive;
    public static volatile SingularAttribute<ApartmentUser, ApartmentResident> apartmentResident;
    public static volatile SingularAttribute<ApartmentUser, String> firstName;
    public static volatile SingularAttribute<ApartmentUser, String> password;
    public static volatile SingularAttribute<ApartmentUser, Date> createdDate;
    public static volatile SingularAttribute<ApartmentUser, String> phone;
    public static volatile SingularAttribute<ApartmentUser, ApartmentAdmin> apartmentAdmin;
    public static volatile SingularAttribute<ApartmentUser, Integer> id;
    public static volatile SingularAttribute<ApartmentUser, String> email;
    public static volatile SingularAttribute<ApartmentUser, String> username;

}