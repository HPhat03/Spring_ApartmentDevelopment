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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_other_member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentOtherMember.findAll", query = "SELECT a FROM ApartmentOtherMember a"),
    @NamedQuery(name = "ApartmentOtherMember.findById", query = "SELECT a FROM ApartmentOtherMember a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentOtherMember.findByName", query = "SELECT a FROM ApartmentOtherMember a WHERE a.name = :name"),
    @NamedQuery(name = "ApartmentOtherMember.findByRelationship", query = "SELECT a FROM ApartmentOtherMember a WHERE a.relationship = :relationship")})
public class ApartmentOtherMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "relationship")
    private String relationship;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentRentalConstract apartmentId;

    public ApartmentOtherMember() {
    }

    public ApartmentOtherMember(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
        if (!(object instanceof ApartmentOtherMember)) {
            return false;
        }
        ApartmentOtherMember other = (ApartmentOtherMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentOtherMember[ id=" + id + " ]";
    }
    
}
