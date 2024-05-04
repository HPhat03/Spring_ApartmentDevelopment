package com.mhp_btn.pojo;

import com.mhp_btn.pojo.ApartmentAdmin;
import com.mhp_btn.pojo.ApartmentResident;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-05T00:00:47")
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