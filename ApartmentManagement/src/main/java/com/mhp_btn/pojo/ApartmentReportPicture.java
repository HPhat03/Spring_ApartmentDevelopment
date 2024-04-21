/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_report_picture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentReportPicture.findAll", query = "SELECT a FROM ApartmentReportPicture a"),
    @NamedQuery(name = "ApartmentReportPicture.findById", query = "SELECT a FROM ApartmentReportPicture a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentReportPicture.findByPicture", query = "SELECT a FROM ApartmentReportPicture a WHERE a.picture = :picture")})
public class ApartmentReportPicture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "picture")
    private String picture;
    @JoinColumn(name = "report_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentDetailReport reportId;

    public ApartmentReportPicture() {
    }

    public ApartmentReportPicture(Integer id) {
        this.id = id;
    }

    public ApartmentReportPicture(Integer id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ApartmentDetailReport getReportId() {
        return reportId;
    }

    public void setReportId(ApartmentDetailReport reportId) {
        this.reportId = reportId;
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
        if (!(object instanceof ApartmentReportPicture)) {
            return false;
        }
        ApartmentReportPicture other = (ApartmentReportPicture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentReportPicture[ id=" + id + " ]";
    }
    
}
