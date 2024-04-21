/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_detail_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentDetailRequest.findAll", query = "SELECT a FROM ApartmentDetailRequest a"),
    @NamedQuery(name = "ApartmentDetailRequest.findById", query = "SELECT a FROM ApartmentDetailRequest a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentDetailRequest.findByQuestion", query = "SELECT a FROM ApartmentDetailRequest a WHERE a.question = :question"),
    @NamedQuery(name = "ApartmentDetailRequest.findByScoreBand", query = "SELECT a FROM ApartmentDetailRequest a WHERE a.scoreBand = :scoreBand")})
public class ApartmentDetailRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "score_band")
    private String scoreBand;
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentSurveyRequest requestId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId")
    private Set<ApartmentDetailResponse> apartmentDetailResponseSet;

    public ApartmentDetailRequest() {
    }

    public ApartmentDetailRequest(Integer id) {
        this.id = id;
    }

    public ApartmentDetailRequest(Integer id, String question, String scoreBand) {
        this.id = id;
        this.question = question;
        this.scoreBand = scoreBand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getScoreBand() {
        return scoreBand;
    }

    public void setScoreBand(String scoreBand) {
        this.scoreBand = scoreBand;
    }

    public ApartmentSurveyRequest getRequestId() {
        return requestId;
    }

    public void setRequestId(ApartmentSurveyRequest requestId) {
        this.requestId = requestId;
    }

    @XmlTransient
    public Set<ApartmentDetailResponse> getApartmentDetailResponseSet() {
        return apartmentDetailResponseSet;
    }

    public void setApartmentDetailResponseSet(Set<ApartmentDetailResponse> apartmentDetailResponseSet) {
        this.apartmentDetailResponseSet = apartmentDetailResponseSet;
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
        if (!(object instanceof ApartmentDetailRequest)) {
            return false;
        }
        ApartmentDetailRequest other = (ApartmentDetailRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentDetailRequest[ id=" + id + " ]";
    }
    
}
