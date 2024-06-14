/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mhp_btn.serializers.UserSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.http.converter.json.MappingJacksonValue;

/**
 *
 * @author Admin
 */

@Entity
@JsonFilter("CONSTRACT_FILTER")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
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
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentOtherMember> apartmentOtherMemberSet;
    @JsonIgnore
    @OneToMany(mappedBy = "apartmentId")
    private Set<ApartmentReport> apartmentReportSet;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentServiceConstract> apartmentServiceConstractSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentSmartCabinet> apartmentSmartCabinetSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentRelativeRegistry> apartmentRelativeRegistrySet;
    @JoinColumn(name = "resident_id", referencedColumnName = "user_id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private ApartmentResident residentId;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private ApartmentRoom roomId;
    @OneToMany(mappedBy = "apartmentId")
    @JsonIgnore
    private Set<ApartmentSurveyResponse> apartmentSurveyResponseSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Set<ApartmentReceipt> apartmentReceiptSet;

    @Transient
    @JsonIgnore
    private String customerName;
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
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
    @JsonProperty("other_members")
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
    @JsonProperty("services")
    public Set<ApartmentService> getService(){
        Set<ApartmentService> s = new HashSet<>();
        this.apartmentServiceConstractSet.forEach(x -> s.add(x.getServiceId()));
        return s;
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
    @JsonProperty("resident")
    public ApartmentUser getResidentUser(){
        return this.residentId.getApartmentUser();
    }
    public void setResidentId(ApartmentResident residentId) {
        this.residentId = residentId;
    }
    @JsonProperty("room")
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
