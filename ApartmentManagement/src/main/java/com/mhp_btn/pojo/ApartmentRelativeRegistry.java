/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_relative_registry")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentRelativeRegistry.findAll", query = "SELECT a FROM ApartmentRelativeRegistry a"),
    @NamedQuery(name = "ApartmentRelativeRegistry.findById", query = "SELECT a FROM ApartmentRelativeRegistry a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentRelativeRegistry.findByName", query = "SELECT a FROM ApartmentRelativeRegistry a WHERE a.name = :name"),
    @NamedQuery(name = "ApartmentRelativeRegistry.findByStartDate", query = "SELECT a FROM ApartmentRelativeRegistry a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "ApartmentRelativeRegistry.findByEndDate", query = "SELECT a FROM ApartmentRelativeRegistry a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "ApartmentRelativeRegistry.findByActive", query = "SELECT a FROM ApartmentRelativeRegistry a WHERE a.active = :active")})
public class ApartmentRelativeRegistry implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private short active;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentRentalConstract apartmentId;

    public ApartmentRelativeRegistry() {
    }

    public ApartmentRelativeRegistry(Integer id) {
        this.id = id;
    }

    public ApartmentRelativeRegistry(Integer id, String name, Date startDate, Date endDate, short active) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
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
        if (!(object instanceof ApartmentRelativeRegistry)) {
            return false;
        }
        ApartmentRelativeRegistry other = (ApartmentRelativeRegistry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentRelativeRegistry[ id=" + id + " ]";
    }
    
}
