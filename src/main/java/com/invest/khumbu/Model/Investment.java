package com.invest.khumbu.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient // This tells Hibernate NOT to save this field in the database
    private String companyLogoBase64;

    private String name;
    private String description;
    private String termLength;
    private String riskRating;
    private Double minInvestment;
    private Double maxInvestment;
    //added values
    private Double roi;
    private String status;

    @Lob // Tells Hibernate to store this field as a BLOB (Binary Large Object)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] companyLogo;

    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MakeInvestment> makeInvestments;

    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupInvestment> GroupInvestments;

    // Constructor
    public Investment(String name, String description, String termLength, String riskRating, 
                      Double minInvestment, Double maxInvestment, byte[] companyLogo, Double roi, String status) {
        this.name = name;
        this.description = description;
        this.termLength = termLength;
        this.riskRating = riskRating;
        this.minInvestment = minInvestment;
        this.maxInvestment = maxInvestment;
        this.companyLogo = companyLogo;
        this.roi = roi;
        this.status = status;
        this.makeInvestments = new ArrayList<>();
        
    }

    // Default constructor
    public Investment() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyLogoBase64() {
        return companyLogoBase64;
    }

    public void setCompanyLogoBase64(String companyLogoBase64) {
        this.companyLogoBase64 = companyLogoBase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermLength() {
        return termLength;
    }

    public void setTermLength(String termLength) {
        this.termLength = termLength;
    }

    public String getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(String riskRating) {
        this.riskRating = riskRating;
    }

    public Double getMinInvestment() {
        return minInvestment;
    }

    public void setMinInvestment(Double minInvestment) {
        this.minInvestment = minInvestment;
    }

    public Double getMaxInvestment() {
        return maxInvestment;
    }

    public void setMaxInvestment(Double maxInvestment) {
        this.maxInvestment = maxInvestment;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public Double getRoi() {
        return roi;
    }

    public void setRoi(Double roi) {
        this.roi = roi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MakeInvestment> getMakeInvestments() {
        return makeInvestments;
    }

    public void setMakeInvestments(List<MakeInvestment> makeInvestments) {
        this.makeInvestments = makeInvestments;
    }
    

}