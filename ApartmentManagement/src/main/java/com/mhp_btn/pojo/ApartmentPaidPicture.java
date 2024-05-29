/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "apartment_paid_picture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentPaidPicture.findAll", query = "SELECT a FROM ApartmentPaidPicture a"),
    @NamedQuery(name = "ApartmentPaidPicture.findById", query = "SELECT a FROM ApartmentPaidPicture a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentPaidPicture.findByPictureUrl", query = "SELECT a FROM ApartmentPaidPicture a WHERE a.pictureUrl = :pictureUrl")})
public class ApartmentPaidPicture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "picture_url")
    private String pictureUrl;
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentPayment paymentId;

    public ApartmentPaidPicture() {
    }

    public ApartmentPaidPicture(Integer id) {
        this.id = id;
    }

    public ApartmentPaidPicture(Integer id, String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public ApartmentPayment getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(ApartmentPayment paymentId) {
        this.paymentId = paymentId;
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
        if (!(object instanceof ApartmentPaidPicture)) {
            return false;
        }
        ApartmentPaidPicture other = (ApartmentPaidPicture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentPaidPicture[ id=" + id + " ]";
    }
    
}
