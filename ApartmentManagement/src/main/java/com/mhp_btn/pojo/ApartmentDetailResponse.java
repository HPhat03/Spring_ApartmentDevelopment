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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "apartment_detail_response")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApartmentDetailResponse.findAll", query = "SELECT a FROM ApartmentDetailResponse a"),
    @NamedQuery(name = "ApartmentDetailResponse.findById", query = "SELECT a FROM ApartmentDetailResponse a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentDetailResponse.findByAnswer", query = "SELECT a FROM ApartmentDetailResponse a WHERE a.answer = :answer")})
public class ApartmentDetailResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer")
    private int answer;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentDetailRequest questionId;
    @JoinColumn(name = "response_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ApartmentSurveyResponse responseId;

    public ApartmentDetailResponse() {
    }

    public ApartmentDetailResponse(Integer id) {
        this.id = id;
    }

    public ApartmentDetailResponse(Integer id, int answer) {
        this.id = id;
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public ApartmentDetailRequest getQuestionId() {
        return questionId;
    }

    public void setQuestionId(ApartmentDetailRequest questionId) {
        this.questionId = questionId;
    }

    public ApartmentSurveyResponse getResponseId() {
        return responseId;
    }

    public void setResponseId(ApartmentSurveyResponse responseId) {
        this.responseId = responseId;
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
        if (!(object instanceof ApartmentDetailResponse)) {
            return false;
        }
        ApartmentDetailResponse other = (ApartmentDetailResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mhp_btn.pojo.ApartmentDetailResponse[ id=" + id + " ]";
    }
    
}
