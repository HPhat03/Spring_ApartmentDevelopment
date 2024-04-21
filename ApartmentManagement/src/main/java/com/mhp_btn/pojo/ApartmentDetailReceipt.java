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
@Table(name = "apartment_detail_receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentDetailReceipt.findAll", query = "SELECT a FROM ApartmentDetailReceipt a"),
    @NamedQuery(name = "ApartmentDetailReceipt.findById", query = "SELECT a FROM ApartmentDetailReceipt a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentDetailReceipt.findByContent", query = "SELECT a FROM ApartmentDetailReceipt a WHERE a.content = :content"),
    @NamedQuery(name = "ApartmentDetailReceipt.findByPrice", query = "SELECT a FROM ApartmentDetailReceipt a WHERE a.price = :price")})
public class ApartmentDetailReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "price")
    private String price;
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentReceipt receiptId;

    public ApartmentDetailReceipt() {
    }

    public ApartmentDetailReceipt(Integer id) {
        this.id = id;
    }

    public ApartmentDetailReceipt(Integer id, String content, String price) {
        this.id = id;
        this.content = content;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ApartmentReceipt getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(ApartmentReceipt receiptId) {
        this.receiptId = receiptId;
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
        if (!(object instanceof ApartmentDetailReceipt)) {
            return false;
        }
        ApartmentDetailReceipt other = (ApartmentDetailReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentDetailReceipt[ id=" + id + " ]";
    }
    
}
