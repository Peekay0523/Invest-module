package com.invest.khumbu.Controllers;

import com.invest.khumbu.Services.SavingsServices;
import com.invest.khumbu.Model.Savings;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SavingController {

    @Autowired
    private SavingsServices savingsService; 

    @GetMapping("/savings")
    public String showSavingsForm(Model model) {
        model.addAttribute("saving", new Savings());

        BigDecimal totalSavings = savingsService.getSavingsAmount();
        model.addAttribute("totalSavings", totalSavings);
        
        return "savings"; // Returns the Thymeleaf template named "savings.html"
    }

    @PostMapping("/my-savings") 
    public String saveSaving(@ModelAttribute("saving") Savings saving) {
        savingsService.saveSavings(saving);
        return "redirect:/savings";
    }
}

