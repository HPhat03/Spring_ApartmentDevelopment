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
@Table(name = "apartment_usage_number")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentUsageNumber.findAll", query = "SELECT a FROM ApartmentUsageNumber a"),
    @NamedQuery(name = "ApartmentUsageNumber.findById", query = "SELECT a FROM ApartmentUsageNumber a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentUsageNumber.findByType", query = "SELECT a FROM ApartmentUsageNumber a WHERE a.type = :type"),
    @NamedQuery(name = "ApartmentUsageNumber.findByNumber", query = "SELECT a FROM ApartmentUsageNumber a WHERE a.number = :number")})
public class ApartmentUsageNumber implements Serializable {
    public static enum Type{
        ELECTRIC,
        WATER
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number")
    private int number;
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentReceipt receiptId;

    public ApartmentUsageNumber() {
    }

    public ApartmentUsageNumber(Integer id) {
        this.id = id;
    }

    public ApartmentUsageNumber(Integer id, String type, int number) {
        this.id = id;
        this.type = type;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        if (!(object instanceof ApartmentUsageNumber)) {
            return false;
        }
        ApartmentUsageNumber other = (ApartmentUsageNumber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentUsageNumber[ id=" + id + " ]";
    }
    
}
