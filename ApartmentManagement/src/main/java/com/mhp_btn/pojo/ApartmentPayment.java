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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentPayment.findAll", query = "SELECT a FROM ApartmentPayment a"),
    @NamedQuery(name = "ApartmentPayment.findById", query = "SELECT a FROM ApartmentPayment a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentPayment.findByPaymentMethod", query = "SELECT a FROM ApartmentPayment a WHERE a.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "ApartmentPayment.findByCreatedDate", query = "SELECT a FROM ApartmentPayment a WHERE a.createdDate = :createdDate")})
public class ApartmentPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 6)
    @Column(name = "PaymentMethod")
    private String paymentMethod;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "Receipt", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentReceipt receipt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentId")
    private Set<ApartmentPaidPicture> apartmentPaidPictureSet;

    public ApartmentPayment() {
    }

    public ApartmentPayment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ApartmentReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(ApartmentReceipt receipt) {
        this.receipt = receipt;
    }

    @XmlTransient
    public Set<ApartmentPaidPicture> getApartmentPaidPictureSet() {
        return apartmentPaidPictureSet;
    }

    public void setApartmentPaidPictureSet(Set<ApartmentPaidPicture> apartmentPaidPictureSet) {
        this.apartmentPaidPictureSet = apartmentPaidPictureSet;
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
        if (!(object instanceof ApartmentPayment)) {
            return false;
        }
        ApartmentPayment other = (ApartmentPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentPayment[ id=" + id + " ]";
    }
    
}
