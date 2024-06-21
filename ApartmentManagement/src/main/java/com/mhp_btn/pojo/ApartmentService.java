/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonFilter("SERVICE_FILTER")
@Table(name = "apartment_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentService.findAll", query = "SELECT a FROM ApartmentService a"),
    @NamedQuery(name = "ApartmentService.findById", query = "SELECT a FROM ApartmentService a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentService.findByName", query = "SELECT a FROM ApartmentService a WHERE a.name = :name"),
    @NamedQuery(name = "ApartmentService.findByPrice", query = "SELECT a FROM ApartmentService a WHERE a.price = :price"),
    @NamedQuery(name = "ApartmentService.findByIsActive", query = "SELECT a FROM ApartmentService a WHERE a.isActive = :isActive")})
public class ApartmentService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{service.name.nullError}")
    @Size(min = 1, max = 45,message = "{service.name.sizeError}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull(message = "{service.price.nullError}")
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_active")
    private short isActive;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceId")
    @JsonIgnore
    private Set<ApartmentServiceConstract> apartmentServiceConstractSet;

    public ApartmentService() {
    }

    public ApartmentService(Integer id) {
        this.id = id;
    }

    public ApartmentService(Integer id, String name, int price, short isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isActive = isActive;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public short getIsActive() {
        return isActive;
    }

    public void setIsActive(short isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    public Set<ApartmentServiceConstract> getApartmentServiceConstractSet() {
        return apartmentServiceConstractSet;
    }

    public void setApartmentServiceConstractSet(Set<ApartmentServiceConstract> apartmentServiceConstractSet) {
        this.apartmentServiceConstractSet = apartmentServiceConstractSet;
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
        if (!(object instanceof ApartmentService)) {
            return false;
        }
        ApartmentService other = (ApartmentService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentService[ id=" + id + " ]";
    }
    
}
