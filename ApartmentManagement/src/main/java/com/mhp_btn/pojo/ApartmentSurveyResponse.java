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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_survey_response")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentSurveyResponse.findAll", query = "SELECT a FROM ApartmentSurveyResponse a"),
    @NamedQuery(name = "ApartmentSurveyResponse.findById", query = "SELECT a FROM ApartmentSurveyResponse a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentSurveyResponse.findBySubmitDate", query = "SELECT a FROM ApartmentSurveyResponse a WHERE a.submitDate = :submitDate"),
    @NamedQuery(name = "ApartmentSurveyResponse.findByName", query = "SELECT a FROM ApartmentSurveyResponse a WHERE a.name = :name")})
public class ApartmentSurveyResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "submit_date")
    @Temporal(TemporalType.DATE)
    private Date submitDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responseId")
    private Set<ApartmentDetailResponse> apartmentDetailResponseSet;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @ManyToOne
    private ApartmentRentalConstract apartmentId;
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentSurveyRequest surveyId;

    public ApartmentSurveyResponse() {
    }

    public ApartmentSurveyResponse(Integer id) {
        this.id = id;
    }

    public ApartmentSurveyResponse(Integer id, Date submitDate, String name) {
        this.id = id;
        this.submitDate = submitDate;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Set<ApartmentDetailResponse> getApartmentDetailResponseSet() {
        return apartmentDetailResponseSet;
    }

    public void setApartmentDetailResponseSet(Set<ApartmentDetailResponse> apartmentDetailResponseSet) {
        this.apartmentDetailResponseSet = apartmentDetailResponseSet;
    }

    public ApartmentRentalConstract getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ApartmentRentalConstract apartmentId) {
        this.apartmentId = apartmentId;
    }

    public ApartmentSurveyRequest getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(ApartmentSurveyRequest surveyId) {
        this.surveyId = surveyId;
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
        if (!(object instanceof ApartmentSurveyResponse)) {
            return false;
        }
        ApartmentSurveyResponse other = (ApartmentSurveyResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentSurveyResponse[ id=" + id + " ]";
    }
    
}
