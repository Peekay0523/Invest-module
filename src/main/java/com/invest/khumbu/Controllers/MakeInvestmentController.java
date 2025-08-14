package com.invest.khumbu.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.invest.khumbu.Model.Investment;
import com.invest.khumbu.Model.MakeInvestment;
import com.invest.khumbu.Services.InvestmentService;
import com.invest.khumbu.Services.MakeInvestmentService;

@Controller
public class MakeInvestmentController {
    
    @Autowired
    private MakeInvestmentService service;
    @Autowired
    private InvestmentService investmentService;

    @GetMapping("/detailedPortfolio")
    public String showInvestmentPortfolio(Model model) {
        // Fetch all investments
        List<Investment> investments = investmentService.getAllInvestments();
        
        // Fetch all make_investments related to those investments
        List<MakeInvestment> makeInvestments = investmentService.getAllMakeInvestments();

        // Pass data to the frontend
        model.addAttribute("investments", investments);
        model.addAttribute("makeInvestments", makeInvestments);
        //MY CODE!!!
        return "detailedPortfolio"; // Return Thymeleaf template
    }

    


}

