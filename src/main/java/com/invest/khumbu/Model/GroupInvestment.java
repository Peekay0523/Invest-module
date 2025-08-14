package com.invest.khumbu.Model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "make_group_invest")
public class GroupInvestment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invest_group_id")
    private Long id;

    private String investName;
    private String investType;
    private Double investAmount;
    private String remarks;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "investment_id", nullable = false)// This column will store the foreign key for the Investment entity
    private Investment investment;

    public GroupInvestment() {
    }
    
     public GroupInvestment(String investName, String investType, Double investAmount, String remarks, Date date, Investment investment) {
     this.investName = investName;
     this.investType = investType;
     this.investAmount = investAmount;
     this.remarks = remarks;
     this.date = date;
     this.investment = investment;
}

// Getters and Setters
    public Long getId() {
         return id; 
    }
    public void setId(Long id) {
         this.id = id; 
    }

    public String getInvestName() {
         return investName; 
    }
    public void setInvestName(String investName) {
         this.investName = investName; 
    }

    public String getInvestType() {
         return investType; 
    }
    public void setInvestType(String investType) {
         this.investType = investType; 
    }

    public Double getInvestAmount() {
         return investAmount; 
    }
    public void setInvestAmount(Double investAmount) {
         this.investAmount = investAmount; 
    }

    public String getRemarks() {
         return remarks; 
    }
    public void setRemarks(String remarks) {
         this.remarks = remarks; 
    }

    public Date getDate() {
         return date; 
    }
    public void setDate(Date date) {
         this.date = date; 
    }
    public Investment getInvestment() {
     return investment;
    }

    public void setInvestment(Investment investment) {
     this.investment = investment;
    }
}
