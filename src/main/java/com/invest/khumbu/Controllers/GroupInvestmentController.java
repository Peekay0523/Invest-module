package com.invest.khumbu.Controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invest.khumbu.DTO.InvestmentDTO;
import com.invest.khumbu.Model.GroupInvestment;
import com.invest.khumbu.Model.Investment;
import com.invest.khumbu.Services.GroupInvestmentService;
import com.invest.khumbu.Services.InvestmentService;

@Controller
public class GroupInvestmentController {

    @Autowired
    private GroupInvestmentService groupService;
    @Autowired
    private InvestmentService investmentService;
 
    @GetMapping("/makeGroupInvestment")
    public String showInvestmentForm(Model model) {
        List<Investment> investments = investmentService.getAllInvestments();
        model.addAttribute("investments", investments); // Add investments to the model
        model.addAttribute("investment", new GroupInvestment());
        return "makeGroupInvestment";
    }
    @GetMapping("/getInvestment")
    @ResponseBody
    public List<InvestmentDTO> getInvestments() {
    List<Investment> investments = investmentService.getAllInvestments();

    // Convert Investment to InvestmentDTO
    return investments.stream()
            .map(inv -> new InvestmentDTO(inv.getId(), inv.getMinInvestment(), inv.getMaxInvestment()))
            .collect(Collectors.toList());
}

    @PostMapping("/group-investments/save")
    public String saveInvestment(@RequestParam("id") Long investmentId,
        @RequestParam("investType") String investType,
        @RequestParam("investAmount") Double investAmount,
        @RequestParam("remarks") String remarks,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date,
        Model model) {  // ✅ Automatically converts

    Investment investment = investmentService.getInvestmentById(investmentId);
    String investName = investment.getName();
    String type = investment.getDescription();
    //String investName = investmentService.getInvestName(investmentId);

    List<Investment> investments = investmentService.getAllInvestments();

    if(investAmount < investment.getMinInvestment() || investAmount > investment.getMaxInvestment()) {
    model.addAttribute("errorMessage",
        "Invalid amount. Please enter amount between " 
        + investment.getMinInvestment() + " and " + investment.getMaxInvestment());
    
    // Add investments list again
    
    model.addAttribute("investments", investments);

    return "makeGroupInvestment"; // Thymeleaf can now render dropdowns
}

    if(!investType.equals(investment.getDescription())){
        model.addAttribute("errorMessage","investment Type does not correspond with selected investment!");
        
        model.addAttribute("investments", investments);
        return "makeGroupInvestment";
    }

    
    GroupInvestment groupInvestment = new GroupInvestment();
    groupInvestment.setInvestment(investment);  
    groupInvestment.setInvestName(investName);
    groupInvestment.setInvestType(type);
    groupInvestment.setInvestAmount(investAmount);
    groupInvestment.setRemarks(remarks);
    groupInvestment.setDate(new Date());  // ✅ No need for manual parsing

    groupService.saveInvestment(groupInvestment);
    model.addAttribute("successMessage" ,  "Investment successfully created!");
    
        return "redirect:/makeGroupInvestment";
    }

    

}
