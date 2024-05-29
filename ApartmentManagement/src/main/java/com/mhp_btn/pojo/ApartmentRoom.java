/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@JsonFilter("ROOM_FILTER")
@Table(name = "apartment_room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentRoom.findAll", query = "SELECT a FROM ApartmentRoom a"),
    @NamedQuery(name = "ApartmentRoom.findById", query = "SELECT a FROM ApartmentRoom a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentRoom.findByRoomNumber", query = "SELECT a FROM ApartmentRoom a WHERE a.roomNumber = :roomNumber"),
    @NamedQuery(name = "ApartmentRoom.findByIsBlank", query = "SELECT a FROM ApartmentRoom a WHERE a.isBlank = :isBlank"),
    @NamedQuery(name = "ApartmentRoom.findByPrice", query = "SELECT a FROM ApartmentRoom a WHERE a.price = :price"),
    @NamedQuery(name = "ApartmentRoom.findByIsActive", query = "SELECT a FROM ApartmentRoom a WHERE a.isActive = :isActive"),
    @NamedQuery(name = "ApartmentRoom.findByCreatedDate", query = "SELECT a FROM ApartmentRoom a WHERE a.createdDate = :createdDate")})
public class ApartmentRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "room_number")
    private String roomNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_blank")
    private short isBlank;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Column(name = "is_active")
    private Short isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JsonIgnore
    @OneToMany(mappedBy = "roomId")
    private Set<ApartmentRentalConstract> apartmentRentalConstractSet;
    @JsonIgnore
    @JoinColumn(name = "floor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentFloor floor;

    public ApartmentRoom() {
    }




    public ApartmentRoom(Integer id) {
        this.id = id;
    }

    public ApartmentRoom(Integer id, String roomNumber, short isBlank, int price, Date createdDate) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.isBlank = isBlank;
        this.price = price;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public short getIsBlank() {
        return isBlank;
    }

    public void setIsBlank(short isBlank) {
        this.isBlank = isBlank;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Short getIsActive() {
        return isActive;
    }

    public void setIsActive(Short isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Set<ApartmentRentalConstract> getApartmentRentalConstractSet() {
        return apartmentRentalConstractSet;
    }

    public void setApartmentRentalConstractSet(Set<ApartmentRentalConstract> apartmentRentalConstractSet) {
        this.apartmentRentalConstractSet = apartmentRentalConstractSet;
    }

    public ApartmentFloor getFloor() {
        return floor;
    }

    public void setFloor(ApartmentFloor floor) {
        this.floor = floor;
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
        if (!(object instanceof ApartmentRoom)) {
            return false;
        }
        ApartmentRoom other = (ApartmentRoom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentRoom[ id=" + id + " ]";
    }
    
}
