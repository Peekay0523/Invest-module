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
    public String saveInvestment(@RequestParam("investment.id") Long investmentId,
        @RequestParam("investType") String investType,
        @RequestParam("investAmount") Double investAmount,
        @RequestParam("remarks") String remarks,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date) {  // ✅ Automatically converts

    Investment investment = investmentService.getInvestmentById(investmentId);
    String investName = investment.getName();
    String type = investment.getDescription();
    //String investName = investmentService.getInvestName(investmentId);

    
    GroupInvestment groupInvestment = new GroupInvestment();
    groupInvestment.setInvestment(investment);  
    groupInvestment.setInvestName(investName);
    groupInvestment.setInvestType(type);
    groupInvestment.setInvestAmount(investAmount);
    groupInvestment.setRemarks(remarks);
    groupInvestment.setDate(date);  // ✅ No need for manual parsing

    groupService.saveInvestment(groupInvestment);
    
        return "redirect:/makeGroupInvestment";
    }

    

}
