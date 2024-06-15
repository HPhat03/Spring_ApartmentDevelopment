/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_resident")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentResident.findAll", query = "SELECT a FROM ApartmentResident a"),
    @NamedQuery(name = "ApartmentResident.findByUserId", query = "SELECT a FROM ApartmentResident a WHERE a.userId = :userId"),
    @NamedQuery(name = "ApartmentResident.findByJoinedDate", query = "SELECT a FROM ApartmentResident a WHERE a.joinedDate = :joinedDate")})
public class ApartmentResident implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "first_login")
    private short firstLogin;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    @JsonIgnore
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "joined_date")
    @Temporal(TemporalType.DATE)
    private Date joinedDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    @OneToOne(optional = false)
    private ApartmentUser apartmentUser;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residentId")
    private Set<ApartmentRentalConstract> apartmentRentalConstractSet;

    public ApartmentResident() {
    }

    public ApartmentResident(Integer userId) {
        this.userId = userId;
    }

    public ApartmentResident(Integer userId, Date joinedDate) {
        this.userId = userId;
        this.joinedDate = joinedDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public ApartmentUser getApartmentUser() {
        return apartmentUser;
    }

    public void setApartmentUser(ApartmentUser apartmentUser) {
        this.apartmentUser = apartmentUser;
    }

    @XmlTransient
    public Set<ApartmentRentalConstract> getApartmentRentalConstractSet() {
        return apartmentRentalConstractSet;
    }

    public void setApartmentRentalConstractSet(Set<ApartmentRentalConstract> apartmentRentalConstractSet) {
        this.apartmentRentalConstractSet = apartmentRentalConstractSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApartmentResident)) {
            return false;
        }
        ApartmentResident other = (ApartmentResident) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentResident[ userId=" + userId + " ]";
    }

    public short getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(short firstLogin) {
        this.firstLogin = firstLogin;
    }
    
}
