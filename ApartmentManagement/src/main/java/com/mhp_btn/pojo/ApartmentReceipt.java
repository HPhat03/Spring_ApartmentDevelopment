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
@Table(name = "apartment_receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentReceipt.findAll", query = "SELECT a FROM ApartmentReceipt a"),
    @NamedQuery(name = "ApartmentReceipt.findById", query = "SELECT a FROM ApartmentReceipt a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentReceipt.findByMonth", query = "SELECT a FROM ApartmentReceipt a WHERE a.month = :month"),
    @NamedQuery(name = "ApartmentReceipt.findByCreatedDate", query = "SELECT a FROM ApartmentReceipt a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "ApartmentReceipt.findByTotal", query = "SELECT a FROM ApartmentReceipt a WHERE a.total = :total")})
public class ApartmentReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "month")
    private Integer month;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptId")
    private Set<ApartmentDetailReceipt> apartmentDetailReceiptSet;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentRentalConstract apartmentId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
    
}
