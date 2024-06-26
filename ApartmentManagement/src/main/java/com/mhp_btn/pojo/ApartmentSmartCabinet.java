/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_smart_cabinet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentSmartCabinet.findAll", query = "SELECT a FROM ApartmentSmartCabinet a"),
    @NamedQuery(name = "ApartmentSmartCabinet.findById", query = "SELECT a FROM ApartmentSmartCabinet a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentSmartCabinet.findByStatus", query = "SELECT a FROM ApartmentSmartCabinet a WHERE a.status = :status"),
    @NamedQuery(name = "ApartmentSmartCabinet.findByCreatedDate", query = "SELECT a FROM ApartmentSmartCabinet a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "ApartmentSmartCabinet.findByUpdatedDate", query = "SELECT a FROM ApartmentSmartCabinet a WHERE a.updatedDate = :updatedDate")})
public class ApartmentSmartCabinet implements Serializable {
    public static enum Status{
        PENDING,
        RECEIVCED;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message="{cabinet.desc.nullErr}")
    @Lob
    @Size(min = 1, max = 65535,message="{cabinet.desc.nullErr}")
    @Column(name = "decription")
    private String decription;
    @Basic(optional = false)
    @NotNull(message="{cabinet.status.nullErr}")
    @Size(min = 1, max = 9)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull(message = "{cabinet.status.nullErr}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy")
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    @NotNull(message= "{cabinet.apartmentId.nullErr}")
    private ApartmentRentalConstract apartmentId;

    public ApartmentSmartCabinet() {
    }

    public ApartmentSmartCabinet(Integer id) {
        this.id = id;
    }

    public ApartmentSmartCabinet(Integer id, String decription, String status, Date createdDate, Date updatedDate) {
        this.id = id;
        this.decription = decription;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        if (this.createdDate != null) {
            return new Date(this.createdDate.getTime());
        }
        return null;
    }

    public Date getUpdatedDate() {
        if (this.updatedDate != null) {
            return new Date(this.updatedDate.getTime());
        }
        return null;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }



    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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
        if (!(object instanceof ApartmentSmartCabinet)) {
            return false;
        }
        ApartmentSmartCabinet other = (ApartmentSmartCabinet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentSmartCabinet[ id=" + id + " ]";
    }
    
}
