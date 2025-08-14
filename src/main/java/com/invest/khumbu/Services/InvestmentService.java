package com.invest.khumbu.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invest.khumbu.Model.Investment;
import com.invest.khumbu.Model.MakeInvestment;
import com.invest.khumbu.Repository.InvestmentRepository;
import com.invest.khumbu.Repository.MakeInvestmentRepository;
import java.util.Date;
import java.util.Optional;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final MakeInvestmentRepository makeInvestmentRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, MakeInvestmentRepository makeInvestmentRepository) {
        this.investmentRepository = investmentRepository;
        this.makeInvestmentRepository = makeInvestmentRepository;
    }

    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    public List<MakeInvestment> getAllMakeInvestments() {
        return makeInvestmentRepository.findAll();
    }

    public void saveInvestment(Investment investment) {
        investmentRepository.save(investment);
    }

    public Investment getInvestmentById(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }
    //my method
    public Optional<Investment> getInvestById(Long id) {
        return investmentRepository.findById(id);
    }

    public void deleteInvestment(Long investmentId) {
        investmentRepository.deleteById(investmentId);
    }

        public MakeInvestment createMakeInvestment(Long investmentId, String investName, String investType,
                                               Double investAmount, String remarks, Date date) {
        Investment investment = investmentRepository.findById(investmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid investment ID"));

        MakeInvestment makeInvestment = new MakeInvestment();
        makeInvestment.setInvestName(investName);
        makeInvestment.setInvestType(investType);
        makeInvestment.setInvestAmount(investAmount);
        makeInvestment.setRemarks(remarks);
        makeInvestment.setDate(date);
        makeInvestment.setInvestment(investment); // Linking the Investment entity to MakeInvestment

        return makeInvestmentRepository.save(makeInvestment);
    }

    
}


