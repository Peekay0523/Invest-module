package com.invest.khumbu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 
import com.invest.khumbu.Model.Savings;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
    
}
