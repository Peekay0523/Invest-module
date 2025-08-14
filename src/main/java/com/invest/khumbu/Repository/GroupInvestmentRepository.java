package com.invest.khumbu.Repository;

import com.invest.khumbu.Model.GroupInvestment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupInvestmentRepository extends JpaRepository<GroupInvestment, Long> {
}
