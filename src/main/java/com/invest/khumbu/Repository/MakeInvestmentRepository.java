package com.invest.khumbu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invest.khumbu.Model.MakeInvestment;
import java.util.List;

@Repository
public interface MakeInvestmentRepository extends JpaRepository<MakeInvestment, Long> {
    //List<MakeInvestment> findByInvestmentId(Long investmentId);
}
