/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "apartment_survey_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentSurveyRequest.findAll", query = "SELECT a FROM ApartmentSurveyRequest a"),
    @NamedQuery(name = "ApartmentSurveyRequest.findById", query = "SELECT a FROM ApartmentSurveyRequest a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentSurveyRequest.findByStartDate", query = "SELECT a FROM ApartmentSurveyRequest a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "ApartmentSurveyRequest.findByEndDate", query = "SELECT a FROM ApartmentSurveyRequest a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "ApartmentSurveyRequest.findByIsActive", query = "SELECT a FROM ApartmentSurveyRequest a WHERE a.isActive = :isActive")})
public class ApartmentSurveyRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
    @JoinColumn(name = "admin_id", referencedColumnName = "user_id")
    @ManyToOne
    private ApartmentAdmin adminId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
    private Set<ApartmentDetailRequest> apartmentDetailRequestSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyId")
    private Set<ApartmentSurveyResponse> apartmentSurveyResponseSet;

    public ApartmentSurveyRequest() {
    }

    public ApartmentSurveyRequest(Integer id) {
        this.id = id;
    }

    public ApartmentSurveyRequest(Integer id, Date startDate, Date endDate, short isActive) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public ApartmentAdmin getAdminId() {
        return adminId;
    }

    public void setAdminId(ApartmentAdmin adminId) {
        this.adminId = adminId;
    }

    @XmlTransient
    public Set<ApartmentDetailRequest> getApartmentDetailRequestSet() {
        return apartmentDetailRequestSet;
    }

    public void setApartmentDetailRequestSet(Set<ApartmentDetailRequest> apartmentDetailRequestSet) {
        this.apartmentDetailRequestSet = apartmentDetailRequestSet;
    }

    @XmlTransient
    public Set<ApartmentSurveyResponse> getApartmentSurveyResponseSet() {
        return apartmentSurveyResponseSet;
    }

    public void setApartmentSurveyResponseSet(Set<ApartmentSurveyResponse> apartmentSurveyResponseSet) {
        this.apartmentSurveyResponseSet = apartmentSurveyResponseSet;
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
        if (!(object instanceof ApartmentSurveyRequest)) {
            return false;
        }
        ApartmentSurveyRequest other = (ApartmentSurveyRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentSurveyRequest[ id=" + id + " ]";
    }
    
}
