package com.invest.khumbu.DTO;

public class InvestmentDTO {
    private Long id;
    private Double minInvestment;
    private Double maxInvestment;

    public InvestmentDTO(Long id, Double minInvestment, Double maxInvestment) {
        this.id = id;
        this.minInvestment = minInvestment;
        this.maxInvestment = maxInvestment;
    }

    // Getters
    public Long getId() { return id; }
    public Double getMinInvestment() { return minInvestment; }
    public Double getMaxInvestment() { return maxInvestment; }
}

