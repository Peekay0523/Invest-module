package com.invest.khumbu.Model;
import java.time.LocalDate;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "savings")
public class Savings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDate date;
    private BigDecimal savingAmount;
    
    public Savings() {
    }

    public Savings(LocalDate date, BigDecimal savingAmount) {
        this.date = date;
        this.savingAmount = savingAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(BigDecimal savingAmount) {
        this.savingAmount = savingAmount;
    }
   
    
}
