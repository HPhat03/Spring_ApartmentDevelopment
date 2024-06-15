/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@JsonFilter("USER_FILTER")
@Table(name = "apartment_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentUser.findAll", query = "SELECT a FROM ApartmentUser a"),
    @NamedQuery(name = "ApartmentUser.findById", query = "SELECT a FROM ApartmentUser a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentUser.findByLastName", query = "SELECT a FROM ApartmentUser a WHERE a.lastName = :lastName"),
    @NamedQuery(name = "ApartmentUser.findByFirstName", query = "SELECT a FROM ApartmentUser a WHERE a.firstName = :firstName"),
    @NamedQuery(name = "ApartmentUser.findByBirthdate", query = "SELECT a FROM ApartmentUser a WHERE a.birthdate = :birthdate"),
    @NamedQuery(name = "ApartmentUser.findByGender", query = "SELECT a FROM ApartmentUser a WHERE a.gender = :gender"),
    @NamedQuery(name = "ApartmentUser.findByEmail", query = "SELECT a FROM ApartmentUser a WHERE a.email = :email"),
    @NamedQuery(name = "ApartmentUser.findByPhone", query = "SELECT a FROM ApartmentUser a WHERE a.phone = :phone"),
    @NamedQuery(name = "ApartmentUser.findByAvatar", query = "SELECT a FROM ApartmentUser a WHERE a.avatar = :avatar"),
    @NamedQuery(name = "ApartmentUser.findByIsActive", query = "SELECT a FROM ApartmentUser a WHERE a.isActive = :isActive"),
    @NamedQuery(name = "ApartmentUser.findByCreatedDate", query = "SELECT a FROM ApartmentUser a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "ApartmentUser.findByRole", query = "SELECT a FROM ApartmentUser a WHERE a.role = :role"),
    @NamedQuery(name = "ApartmentUser.findByUsername", query = "SELECT a FROM ApartmentUser a WHERE a.username = :username"),
    @NamedQuery(name = "ApartmentUser.findByPassword", query = "SELECT a FROM ApartmentUser a WHERE a.password = :password")})
public class ApartmentUser implements Serializable {
    public static String RESIDENT = "RESIDENT";
    public static String ADMIN = "ADMIN";
    public static String RESIDENT_DEFAULT_PASSWORD = "NO_LOGIN";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "last_name")
    @JsonIgnore
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "first_name")
    @JsonIgnore 
    private String firstName;
    @Transient
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender")
    private short gender;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 11)
    @NotNull
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apartmentUser")
    @JsonIgnore
    private ApartmentAdmin apartmentAdmin;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "apartmentUser")
    @JsonIgnore
    private ApartmentResident apartmentResident;
    
    
    @Transient
    @JsonIgnore
    private MultipartFile file;
    
    
    @Transient
    @JsonIgnore
    private String confirmPassword;
    public ApartmentUser() {
    }
    @JsonProperty("first_login")
    public short getMoreInfo(){
        if (this.role.equals(ApartmentUser.RESIDENT))
            return this.apartmentResident.getFirstLogin();
        else
            return (short) 0;
    }

    public ApartmentUser(Integer id) {
        this.id = id;
    }

    public ApartmentUser(Integer id, String lastName, String firstName, Date birthdate, short gender, short isActive, String role, String username, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.isActive = isActive;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName(){
        return this.lastName + " " + this.firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getBirthdate() {
        this.birthdate.setDate(this.birthdate.getDate() + 1);
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return this.gender==1 ? "Nam" : "Nữ";
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApartmentAdmin getApartmentAdmin() {
        return apartmentAdmin;
    }

    public void setApartmentAdmin(ApartmentAdmin apartmentAdmin) {
        this.apartmentAdmin = apartmentAdmin;
    }

    public ApartmentResident getApartmentResident() {
        return apartmentResident;
    }

    public void setApartmentResident(ApartmentResident apartmentResident) {
        this.apartmentResident = apartmentResident;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApartmentUser)) {
            return false;
        }
        ApartmentUser other = (ApartmentUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentUser[ id=" + id + " ]";
    }

    

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
