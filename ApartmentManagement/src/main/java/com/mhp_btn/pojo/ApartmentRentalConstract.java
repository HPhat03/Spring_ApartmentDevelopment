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
@Table(name = "apartment_rental_constract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentRentalConstract.findAll", query = "SELECT a FROM ApartmentRentalConstract a"),
    @NamedQuery(name = "ApartmentRentalConstract.findById", query = "SELECT a FROM ApartmentRentalConstract a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentRentalConstract.findByStatus", query = "SELECT a FROM ApartmentRentalConstract a WHERE a.status = :status"),
    @NamedQuery(name = "ApartmentRentalConstract.findByCreatedDate", query = "SELECT a FROM ApartmentRentalConstract a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "ApartmentRentalConstract.findByIsActive", query = "SELECT a FROM ApartmentRentalConstract a WHERE a.isActive = :isActive"),
    @NamedQuery(name = "ApartmentRentalConstract.findByFinalPrice", query = "SELECT a FROM ApartmentRentalConstract a WHERE a.finalPrice = :finalPrice")})
public class ApartmentRentalConstract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "final_price")
    private int finalPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentOtherMember> apartmentOtherMemberSet;
    @OneToMany(mappedBy = "apartmentId")
    private Set<ApartmentReport> apartmentReportSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentServiceConstract> apartmentServiceConstractSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentSmartCabinet> apartmentSmartCabinetSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentRelativeRegistry> apartmentRelativeRegistrySet;
    @JoinColumn(name = "resident_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private ApartmentResident residentId;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne
    private ApartmentRoom roomId;
    @OneToMany(mappedBy = "apartmentId")
    private Set<ApartmentSurveyResponse> apartmentSurveyResponseSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentReceipt> apartmentReceiptSet;

    public ApartmentRentalConstract() {
    }

    public ApartmentRentalConstract(Integer id) {
        this.id = id;
    }

    public ApartmentRentalConstract(Integer id, String status, Date createdDate, short isActive, int finalPrice) {
        this.id = id;
        this.status = status;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.finalPrice = finalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    @XmlTransient
    public Set<ApartmentOtherMember> getApartmentOtherMemberSet() {
        return apartmentOtherMemberSet;
    }

    public void setApartmentOtherMemberSet(Set<ApartmentOtherMember> apartmentOtherMemberSet) {
        this.apartmentOtherMemberSet = apartmentOtherMemberSet;
    }

    @XmlTransient
    public Set<ApartmentReport> getApartmentReportSet() {
        return apartmentReportSet;
    }

    public void setApartmentReportSet(Set<ApartmentReport> apartmentReportSet) {
        this.apartmentReportSet = apartmentReportSet;
    }

    @XmlTransient
    public Set<ApartmentServiceConstract> getApartmentServiceConstractSet() {
        return apartmentServiceConstractSet;
    }

    public void setApartmentServiceConstractSet(Set<ApartmentServiceConstract> apartmentServiceConstractSet) {
        this.apartmentServiceConstractSet = apartmentServiceConstractSet;
    }

    @XmlTransient
    public Set<ApartmentSmartCabinet> getApartmentSmartCabinetSet() {
        return apartmentSmartCabinetSet;
    }

    public void setApartmentSmartCabinetSet(Set<ApartmentSmartCabinet> apartmentSmartCabinetSet) {
        this.apartmentSmartCabinetSet = apartmentSmartCabinetSet;
    }

    @XmlTransient
    public Set<ApartmentRelativeRegistry> getApartmentRelativeRegistrySet() {
        return apartmentRelativeRegistrySet;
    }

    public void setApartmentRelativeRegistrySet(Set<ApartmentRelativeRegistry> apartmentRelativeRegistrySet) {
        this.apartmentRelativeRegistrySet = apartmentRelativeRegistrySet;
    }

    public ApartmentResident getResidentId() {
        return residentId;
    }

    public void setResidentId(ApartmentResident residentId) {
        this.residentId = residentId;
    }

    public ApartmentRoom getRoomId() {
        return roomId;
    }

    public void setRoomId(ApartmentRoom roomId) {
        this.roomId = roomId;
    }

    @XmlTransient
    public Set<ApartmentSurveyResponse> getApartmentSurveyResponseSet() {
        return apartmentSurveyResponseSet;
    }

    public void setApartmentSurveyResponseSet(Set<ApartmentSurveyResponse> apartmentSurveyResponseSet) {
        this.apartmentSurveyResponseSet = apartmentSurveyResponseSet;
    }

    @XmlTransient
    public Set<ApartmentReceipt> getApartmentReceiptSet() {
        return apartmentReceiptSet;
    }

    public void setApartmentReceiptSet(Set<ApartmentReceipt> apartmentReceiptSet) {
        this.apartmentReceiptSet = apartmentReceiptSet;
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
        if (!(object instanceof ApartmentRentalConstract)) {
            return false;
        }
        ApartmentRentalConstract other = (ApartmentRentalConstract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentRentalConstract[ id=" + id + " ]";
    }
    
}
