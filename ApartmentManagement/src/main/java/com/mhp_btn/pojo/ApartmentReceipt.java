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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@JsonFilter("RECEIPT_FILTER")
@Table(name = "apartment_receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentReceipt.findAll", query = "SELECT a FROM ApartmentReceipt a"),
    @NamedQuery(name = "ApartmentReceipt.findById", query = "SELECT a FROM ApartmentReceipt a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentReceipt.findByMonth", query = "SELECT a FROM ApartmentReceipt a WHERE a.month = :month"),
    @NamedQuery(name = "ApartmentReceipt.findByCreatedDate", query = "SELECT a FROM ApartmentReceipt a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "ApartmentReceipt.findByTotal", query = "SELECT a FROM ApartmentReceipt a WHERE a.total = :total")})
public class ApartmentReceipt implements Serializable {

    @Column(name = "paid")
    private Short paid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptId", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ApartmentUsageNumber> apartmentUsageNumberSet;

    @Basic(optional = false)
    @NotNull
    @Column(name = "month")
    private int month;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "year")
    private String year;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ApartmentPayment> apartmentPaymentSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptId", fetch = FetchType.EAGER)
    private Set<ApartmentDetailReceipt> apartmentDetailReceiptSet;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private ApartmentRentalConstract apartmentId;
    
    @Transient
    private String name; 
    public ApartmentReceipt() {
    }
    
    public ApartmentReceipt(Integer id) {
        this.id = id;
    }

    public ApartmentReceipt(Integer id, Date createdDate, int total) {
        this.id = id;
        this.createdDate = createdDate;
        this.total = total;
    }
    public String getName(){
        return String.format("Hóa đơn tháng %d/%s phòng %s", this.month, this.year, this.apartmentId.getRoomId().getRoomNumber());
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @XmlTransient
    @JsonProperty("details")
    public Set<ApartmentDetailReceipt> getApartmentDetailReceiptSet() {
        return apartmentDetailReceiptSet;
    }

    public void setApartmentDetailReceiptSet(Set<ApartmentDetailReceipt> apartmentDetailReceiptSet) {
        this.apartmentDetailReceiptSet = apartmentDetailReceiptSet;
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
        if (!(object instanceof ApartmentReceipt)) {
            return false;
        }
        ApartmentReceipt other = (ApartmentReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentReceipt[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<ApartmentPayment> getApartmentPaymentSet() {
        return apartmentPaymentSet;
    }

    public void setApartmentPaymentSet(Set<ApartmentPayment> apartmentPaymentSet) {
        this.apartmentPaymentSet = apartmentPaymentSet;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @XmlTransient
    @JsonProperty("usage")
    public Set<ApartmentUsageNumber> getApartmentUsageNumberSet() {
        return apartmentUsageNumberSet;
    }

    public void setApartmentUsageNumberSet(Set<ApartmentUsageNumber> apartmentUsageNumberSet) {
        this.apartmentUsageNumberSet = apartmentUsageNumberSet;
    }

    public Short getPaid() {
        return paid;
    }

    public void setPaid(Short paid) {
        this.paid = paid;
    }
    
}
