package com.invest.khumbu.Controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.invest.khumbu.DTO.InvestmentDTO;
import com.invest.khumbu.Model.Investment;
import com.invest.khumbu.Model.MakeInvestment;
import com.invest.khumbu.Model.User;
import com.invest.khumbu.Repository.MakeInvestmentRepository;
import com.invest.khumbu.Repository.UserRepository;
import com.invest.khumbu.Services.InvestmentService;
import com.invest.khumbu.Services.MakeInvestmentService;
import com.invest.khumbu.Services.SavingsServices;

@Controller
public class InvestmentController {

    private final InvestmentService investmentService;
    @Autowired
    private final MakeInvestmentService makeInvestmentService;
    private final SavingsServices savingServices;
    private final MakeInvestmentRepository makeInvestmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvestmentController(InvestmentService investmentService, MakeInvestmentService makeInvestmentService, SavingsServices savingServices, MakeInvestmentRepository makeInvestmentRepository, UserRepository userRepository) {
        this.investmentService = investmentService;
        this.makeInvestmentService=makeInvestmentService;
        this.savingServices=savingServices;
        this.makeInvestmentRepository=makeInvestmentRepository;
        this.userRepository=userRepository;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/signup-login")
    public String signuplogin() {
        return "signup-login";
    }
    @GetMapping("/logon/forgot-password")
    public String forgotPassword() {
        return "logon/forgot-password";
    }
    @GetMapping("/logon/login")
    public String login() {
        return "logon/login";
    }
    @GetMapping("/logon/signup")
    public String signup() {
        return "logon/signup";
    }
    @GetMapping("/investDashboard")
    public String investDashboard() {
        return "investDashboard";
    }

    @GetMapping("/options")
    public String showInvestments(Model model) {
        List<Investment> investments = investmentService.getAllInvestments();
    
        // Encode the companyLogo as base64 and set it in companyLogoBase64
        for (Investment investment : investments) {
            if (investment.getCompanyLogo() != null) {
                String base64Image = Base64.getEncoder().encodeToString(investment.getCompanyLogo());
                investment.setCompanyLogoBase64(base64Image);  // Set the Base64 encoded image
            }
        }
    
        model.addAttribute("investments", investments);
        return "options";
    }

    @GetMapping("/groupInvestment")
    public String showInvestment(Model model) {
        List<Investment> investments = investmentService.getAllInvestments();
    
        // Encode the companyLogo as base64 and set it in companyLogoBase64
        for (Investment investment : investments) {
            if (investment.getCompanyLogo() != null) {
                String base64Image = Base64.getEncoder().encodeToString(investment.getCompanyLogo());
                investment.setCompanyLogoBase64(base64Image);  // Set the Base64 encoded image
            }
        }
    
        model.addAttribute("investments", investments);
        return "groupInvestment";
    }

    @GetMapping("/incomeTracking")
    public String incomeTracking() {
        return "incomeTracking";
    }

    @GetMapping("/investmentDetails")
    public String investmentDetails(Model model) {
        
        List<Investment> investments = investmentService.getAllInvestments();  
        model.addAttribute("investments", investments);
        
        return "investmentDetails";
    }

    @GetMapping("/investPortfolio")
    public String investPortfolio() {
        return "investPortfolio";
    }


    @GetMapping("/performance")
    public String performance() {
        return "performance";
    }

    //Performance 
    @GetMapping("/performanceHistory")
    public String performanceHistory(Model model) {
    
        
        BigDecimal total = savingServices.getSavingsAmount();

        List<MakeInvestment> vest = makeInvestmentService.getAllInvestments();
        Double amount=0.0;

        for(int x=0;x<vest.size();x++){
            amount = amount + vest.get(x).getInvestAmount();
        }
        Double firstVal=vest.get(0).getInvestAmount();
        total=total.add(BigDecimal.valueOf(amount+firstVal));
        
        model.addAttribute("sum",total+" ");
        return "performanceHistory";
    }

    @GetMapping("/projections")
    public String projections() {
        return "projections";
    }


    @GetMapping("/myAdmin/adminInvestManage")
    public String adminInvestmentPage() {
        return "myAdmin/adminInvestManage";
    }

    @PostMapping("/investments/upload")
    public String saveInvestment(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("minInvestment") Double minInvestment,
                                 @RequestParam("maxInvestment") Double maxInvestment,
                                 @RequestParam("termLength") String termLength,
                                 @RequestParam("riskRating") String riskRating,
                                 @RequestParam("ROI") Double roi,
                                 @RequestParam("stat") String status,
                                 @RequestParam("companyLogo") MultipartFile companyLogo) throws IOException {

        Investment investment = new Investment();
        investment.setName(name);
        investment.setDescription(description);
        investment.setMinInvestment(minInvestment);
        investment.setMaxInvestment(maxInvestment);
        investment.setTermLength(termLength);
        investment.setRiskRating(riskRating);
        investment.setRoi(roi);
        investment.setStatus(status);
        if (!companyLogo.isEmpty()) {
            investment.setCompanyLogo(companyLogo.getBytes());
        }

        investmentService.saveInvestment(investment);
        return "redirect:/myAdmin/adminInvestManage";
    }


    


    @GetMapping("/myAdmin/adminInvestDashboard")
    public String adminInvestDashBoard() {
        return "myAdmin/adminInvestDashboard";
    }

    
@GetMapping("/myAdmin/adminInvestTrackingPage")
public String adminInvestTrackingPage(Model model) {
    List<Investment> investments = investmentService.getAllInvestments();
    List<MakeInvestment> makeInvestments = makeInvestmentRepository.findAll();

    // Sum by investName
    Map<String, Double> investmentSums = makeInvestments.stream()
        .collect(Collectors.groupingBy(
            MakeInvestment::getInvestName,
            Collectors.reducing(
                0.0,
                MakeInvestment::getInvestAmount,
                Double::sum
            )
        ));

    // Map investment name to Investment object
    Map<String, Investment> investmentDetailsMap = investments.stream()
        .collect(Collectors.toMap(
            Investment::getName,
            investment -> investment
        ));

    model.addAttribute("makeInvestments", makeInvestments);
    model.addAttribute("investmentSums", investmentSums);
    model.addAttribute("investmentDetailsMap", investmentDetailsMap);

    return "myAdmin/adminInvestTrackingPage";
}

    // Current Page i'm working on!!!!!!!!!!!!!!!!!!
    @GetMapping("/myAdmin/adminViewInvestments")
    public String adminViewInvestments(Model model) {
    
    List<MakeInvestment> makeInvestments = makeInvestmentRepository.findAll();
        Map<String, Double> investmentSums = makeInvestments.stream()
        .collect(Collectors.groupingBy(
            MakeInvestment::getInvestName,
            Collectors.reducing(
                0.0,
                MakeInvestment::getInvestAmount,
                Double::sum
            )
        ));

        List<Investment> investments = investmentService.getAllInvestments();
        Map<String, Investment> investmentDetailsMap = investments.stream()
        .collect(Collectors.toMap(
            Investment::getName,
            investment -> investment
        ));

        List<User> user = userRepository.findAll();

        model.addAttribute("investments", investmentSums);
        model.addAttribute("investmentDetails", investmentDetailsMap);
        model.addAttribute("Users",user);
        return "myAdmin/adminViewInvestments";
    }
    //new methods
    @GetMapping("/makeInvestment")
    public String showInvestmentForm(Model model) {
        List<Investment> investments = investmentService.getAllInvestments();
        model.addAttribute("investments", investments); // Add investments to the model
        model.addAttribute("investment", new MakeInvestment());
        return "makeInvestment";

        
    }
@GetMapping("/getInvestments")
@ResponseBody
public List<InvestmentDTO> getInvestments() {
    List<Investment> investments = investmentService.getAllInvestments();

    // Convert Investment to InvestmentDTO
    return investments.stream()
            .map(inv -> new InvestmentDTO(inv.getId(), inv.getMinInvestment(), inv.getMaxInvestment()))
            .collect(Collectors.toList());
}

@PostMapping("/investments/save")
public String saveMakeInvestment(
        @RequestParam("investment.id") Long investmentId,
        @RequestParam("investType") String investType,
        @RequestParam("investAmount") Double investAmount,
        @RequestParam("remarks") String remarks,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date) {  // ✅ Automatically converts

    Investment investment = investmentService.getInvestmentById(investmentId);
    String investName = investment.getName();
    String type = investment.getDescription();
    //String investName = investmentService.getInvestName(investmentId);

    MakeInvestment makeInvestment = new MakeInvestment();
    makeInvestment.setInvestment(investment);  
    makeInvestment.setInvestName(investName);
    makeInvestment.setInvestType(type);
    makeInvestment.setInvestAmount(investAmount);
    makeInvestment.setRemarks(remarks);
    makeInvestment.setDate(date);  // ✅ No need for manual parsing

    makeInvestmentService.saveInvestment(makeInvestment);

    return "redirect:/makeInvestment";
}

}
