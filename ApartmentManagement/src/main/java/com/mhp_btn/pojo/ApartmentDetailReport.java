/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "apartment_detail_report")
@XmlRootElement
@JsonFilter("DETAIL_REPORT_FILTER")
@NamedQueries({
    @NamedQuery(name = "ApartmentDetailReport.findAll", query = "SELECT a FROM ApartmentDetailReport a"),
    @NamedQuery(name = "ApartmentDetailReport.findById", query = "SELECT a FROM ApartmentDetailReport a WHERE a.id = :id")})
public class ApartmentDetailReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private ApartmentReport reportId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportId", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ApartmentReportPicture> apartmentReportPictureSet;

    public ApartmentDetailReport() {
    }

    public ApartmentDetailReport(Integer id) {
        this.id = id;
    }

    public ApartmentDetailReport(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ApartmentReport getReportId() {
        return reportId;
    }

    public void setReportId(ApartmentReport reportId) {
        this.reportId = reportId;
    }

    @XmlTransient
    @JsonProperty("pictures")
    public List<String> getApartmentReportPictureSet() {
        List<String> pic = new ArrayList<>();
        for(ApartmentReportPicture p : this.apartmentReportPictureSet){
            pic.add(p.getPicture());
        }
        return pic;
    }

    public void setApartmentReportPictureSet(Set<ApartmentReportPicture> apartmentReportPictureSet) {
        this.apartmentReportPictureSet = apartmentReportPictureSet;
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
        if (!(object instanceof ApartmentDetailReport)) {
            return false;
        }
        ApartmentDetailReport other = (ApartmentDetailReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentDetailReport[ id=" + id + " ]";
    }
    
}
