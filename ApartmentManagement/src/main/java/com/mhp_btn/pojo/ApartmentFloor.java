/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_floor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentFloor.findAll", query = "SELECT a FROM ApartmentFloor a"),
    @NamedQuery(name = "ApartmentFloor.findById", query = "SELECT a FROM ApartmentFloor a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentFloor.findByName", query = "SELECT a FROM ApartmentFloor a WHERE a.name = :name")})
public class ApartmentFloor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "floor")
    private Set<ApartmentRoom> apartmentRoomSet;

    public ApartmentFloor() {
    }

    public ApartmentFloor(Integer id) {
        this.id = id;
    }

    public ApartmentFloor(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public Set<ApartmentRoom> getApartmentRoomSet() {
        return apartmentRoomSet;
    }

    public void setApartmentRoomSet(Set<ApartmentRoom> apartmentRoomSet) {
        this.apartmentRoomSet = apartmentRoomSet;
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
        if (!(object instanceof ApartmentFloor)) {
            return false;
        }
        ApartmentFloor other = (ApartmentFloor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentFloor[ id=" + id + " ]";
    }
    
}
