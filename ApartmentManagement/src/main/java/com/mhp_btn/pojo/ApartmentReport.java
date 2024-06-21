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
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@JsonFilter("REPORT_FILTER")
@Entity
@Table(name = "apartment_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentReport.findAll", query = "SELECT a FROM ApartmentReport a"),
    @NamedQuery(name = "ApartmentReport.findById", query = "SELECT a FROM ApartmentReport a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentReport.findByCreatedDate", query = "SELECT a FROM ApartmentReport a WHERE a.createdDate = :createdDate")})
public class ApartmentReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @OneToMany(mappedBy = "reportId", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ApartmentDetailReport> apartmentDetailReportSet;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private ApartmentRentalConstract apartmentId;

    public ApartmentReport() {
    }

    public ApartmentReport(Integer id) {
        this.id = id;
    }

    public ApartmentReport(Integer id, Date createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        this.createdDate.setDate(this.createdDate.getDate() + 1);
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    @JsonProperty("details")
    public Set<ApartmentDetailReport> getApartmentDetailReportSet() {
        return apartmentDetailReportSet;
    }

    public void setApartmentDetailReportSet(Set<ApartmentDetailReport> apartmentDetailReportSet) {
        this.apartmentDetailReportSet = apartmentDetailReportSet;
    }

    public ApartmentRentalConstract getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(ApartmentRentalConstract apartmentId) {
        this.apartmentId = apartmentId;
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
        if (!(object instanceof ApartmentReport)) {
            return false;
        }
        ApartmentReport other = (ApartmentReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentReport[ id=" + id + " ]";
    }
    
}
