package com.invest.khumbu.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invest.khumbu.Model.MakeInvestment;
import com.invest.khumbu.Model.Investment;
import com.invest.khumbu.Repository.MakeInvestmentRepository;
import com.invest.khumbu.Repository.InvestmentRepository;

@Service
public class MakeInvestmentService {
    
    @Autowired
    private MakeInvestmentRepository makeInvestmentRepository;

    @Autowired
    private InvestmentRepository investmentRepository;  // Inject InvestmentRepository to fetch Investment data
/* 
    // Save MakeInvestment and link it to an Investment
    public MakeInvestment saveInvestment(Long investmentId, MakeInvestment makeInvestment) {
        // Fetch the Investment object based on the investmentId
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid investment ID"));

        // Set the Investment to MakeInvestment
        makeInvestment.setInvestment(investment);

        // Save the MakeInvestment object
        return makeInvestmentRepository.save(makeInvestment);
    }
*/
    public List<MakeInvestment> getAllInvestments() {
        return makeInvestmentRepository.findAll();
    }
    //new
    public MakeInvestment saveInvestment(MakeInvestment makeInvestment) {
        // Save the MakeInvestment object
        return makeInvestmentRepository.save(makeInvestment);
    }
    

}
