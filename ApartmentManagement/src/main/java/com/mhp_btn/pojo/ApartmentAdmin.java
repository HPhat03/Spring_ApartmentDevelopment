/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentAdmin.findAll", query = "SELECT a FROM ApartmentAdmin a"),
    @NamedQuery(name = "ApartmentAdmin.findByUserId", query = "SELECT a FROM ApartmentAdmin a WHERE a.userId = :userId")})
public class ApartmentAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @OneToMany(mappedBy = "adminId")
    private Set<ApartmentSurveyRequest> apartmentSurveyRequestSet;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ApartmentUser apartmentUser;

    public ApartmentAdmin() {
    }

    public ApartmentAdmin(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Set<ApartmentSurveyRequest> getApartmentSurveyRequestSet() {
        return apartmentSurveyRequestSet;
    }

    public void setApartmentSurveyRequestSet(Set<ApartmentSurveyRequest> apartmentSurveyRequestSet) {
        this.apartmentSurveyRequestSet = apartmentSurveyRequestSet;
    }

    public ApartmentUser getApartmentUser() {
        return apartmentUser;
    }

    public void setApartmentUser(ApartmentUser apartmentUser) {
        this.apartmentUser = apartmentUser;
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
        if (!(object instanceof ApartmentAdmin)) {
            return false;
        }
        ApartmentAdmin other = (ApartmentAdmin) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentAdmin[ userId=" + userId + " ]";
    }
    
}
