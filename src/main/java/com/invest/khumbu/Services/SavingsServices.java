package com.invest.khumbu.Services;

import com.invest.khumbu.Model.Savings;
import com.invest.khumbu.Repository.SavingsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SavingsServices {

    @Autowired
    private SavingsRepository savingRepository;

    public void saveSavings(Savings saving) {
        savingRepository.save(saving);
    }

    public BigDecimal getSavingsAmount() {
        BigDecimal total = BigDecimal.ZERO; // Initialize total properly

        List<Savings> savings = savingRepository.findAll();

        for (Savings saving : savings) {
            total = total.add(saving.getSavingAmount()); // Add each saving amount
        }

        return total;
    }
}

