/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.repositories.implement;

import com.cloudinary.Cloudinary;
import com.mhp_btn.components.CloudinaryUtil;
import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.repositories.UserRepository;
import com.mhp_btn.utils.StringUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<ApartmentUser> getUsers() {
       Session s = this.factory.getObject().getCurrentSession();
       Query q = s.createNamedQuery("ApartmentUser.findAll");
       return q.getResultList();
    }

    @Override
    public ApartmentUser getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM ApartmentUser U WHERE U.username=:un");
        q.setParameter("un", username);
        if (q.getResultList().isEmpty())
            return null;
        return (ApartmentUser) q.getSingleResult();
    }

    @Override
    public void save(ApartmentUser user) {
        Session s = this.factory.getObject().getCurrentSession();
        if (user.getId() != null)
        {
            System.out.println("updating");
             s.update(user);
        }
        else
            s.save(user);
    }

    @Override
    public ApartmentUser getUsersByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ApartmentUser.findById");
        q.setParameter("id", id);
        if (!q.getResultList().isEmpty())
            return (ApartmentUser) q.getSingleResult();
        else
            return null;
    }

    @Override
    public ApartmentUser ChangeOrInitialize(ApartmentUser user, Map<String, String> data, boolean isInit) {
        String firstname = data.get("firstname");
        String lastname = data.get("lastname");
        String phone = data.get("phone");
        String email = data.get("email");
        Date today = new Date();
        String password = data.get("password");
        String role = data.get("role");
        String avatar = user.getAvatar();
        MultipartFile file = user.getFile();
        System.out.println(avatar);
        
        if ((!isInit && firstname != null)||(isInit))
            user.setFirstName(firstname);
        if ((!isInit && lastname != null)||(isInit))
            user.setLastName(lastname);
        if ((!isInit && phone != null)||(isInit))
            user.setPhone(phone);
        if ((!isInit && data.get("gender")!= null)||(isInit))
            user.setGender((short) Integer.parseInt(data.get("gender")));
        if ((!isInit && email!= null)||(isInit))
            user.setEmail(email);
        if (isInit){
            user.setCreatedDate(today);
            user.setIsActive((short) 1);
        }
        if ((!isInit && data.get("birthdate") != null)||(isInit))
        {
            try {
                Date birthdate = StringUtil.dateFormater().parse(data.get("birthdate"));
                user.setBirthdate(birthdate);
            } catch (ParseException ex) {
                Logger.getLogger(UserRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (isInit && role!=null && role.equals(ApartmentUser.ADMIN)){
            user.setRole(ApartmentUser.ADMIN);
            user.setUsername(data.get("username"));
        }else if (isInit){
            user.setRole(ApartmentUser.RESIDENT);
            String[] lastnameArr = lastname.split(" ");
            for (int i = 0; i < lastnameArr.length; i++) {
                lastnameArr[i] = lastnameArr[i].substring(0, 1);
            }
            String username = String.join("", lastnameArr) + firstname;
            user.setUsername(StringUtil.removeAccent(username).toLowerCase());
            user.setPassword(ApartmentUser.RESIDENT_DEFAULT_PASSWORD);
        }
        if ((!isInit && password!=null) || (isInit && role!=null && role.equals(ApartmentUser.ADMIN))){
            user.setPassword(password);
        }
        if (avatar != null && file!= null && !file.isEmpty()){
            try {
                CloudinaryUtil.destroy(avatar, cloudinary);
            } catch (IOException ex) {
                return new ApartmentUser();
            }
        }
        try {
                if (file!=null && !file.isEmpty()) {
                    user.setAvatar(CloudinaryUtil.upload(file, cloudinary));
                }
            } catch (IOException e) {
                    return new ApartmentUser();
            }
        
        return user;
        
    }

    @Override
    public boolean authUser(String username, String password) {
        ApartmentUser u = this.getUserByUsername(username);
        if(u==null)
            return false;
//        System.out.println(this.encoder.encode("123456"));
        return this.encoder.matches(password, u.getPassword());
    }
    
}
