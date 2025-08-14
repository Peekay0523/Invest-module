package com.invest.khumbu.Controllers;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.invest.khumbu.Model.Investment;
import com.invest.khumbu.Model.User;
import com.invest.khumbu.Services.InvestmentService;
import com.invest.khumbu.Services.UserService;


@Controller
public class AdminController {

    private final UserService userService;
    private final InvestmentService investmentService;
    

    @Autowired
    public AdminController(InvestmentService investmentService, UserService userService) {
        this.investmentService = investmentService;
        this.userService = userService;
    }
     
  
    
    @GetMapping("/myAdmin/adminGroupLimit")
    public String showUpdateForm(Model model) {
        // Fetch all investments from the database
        List<Investment> investments = investmentService.getAllInvestments();
        
        // Pass the list of investments to the model
        model.addAttribute("investments", investments);

        return "myAdmin/adminGroupLimit"; // Thymeleaf template
    }
@PostMapping("/admin/investments/update-max")
public String updateInvestment(@RequestParam("investmentName") Long investmentId,
                               @RequestParam("investmentDescription") String investmentDescription,
                               @RequestParam("maxInvestment") Double maxInvestment,
                               RedirectAttributes redirectAttributes) {
    // Fetch the investment using the investment ID
    Investment selectedInvestment = investmentService.getInvestmentById(investmentId);

    if (selectedInvestment != null) {
        // Update the max investment and description
        selectedInvestment.setDescription(investmentDescription);  // Update the description if needed
        selectedInvestment.setMaxInvestment(maxInvestment);  // Update the max investment amount
        investmentService.saveInvestment(selectedInvestment);  // Save the updated investment to the database

        // Add success message as a flash attribute
        redirectAttributes.addFlashAttribute("message", "Investment limit updated successfully!");
    }

    // Redirect to the admin investment management page or any other page
    return "redirect:/myAdmin/adminGroupLimit";
}


    @GetMapping("/myAdmin/adminListInvest")
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
        return "myAdmin/adminListInvest";
    }

    @PostMapping("/admin/investments/delete")
    public String deleteInvestment(@RequestParam("investmentId") Long investmentId, RedirectAttributes redirectAttributes) {
    // Delete the investment from the database
    investmentService.deleteInvestment(investmentId);

    // Add success message as a flash attribute
    redirectAttributes.addFlashAttribute("message", "Investment deleted successfully!");

    // Redirect to the investments list page
    return "redirect:/myAdmin/adminListInvest";
}

@GetMapping("/admin/investments/edit")
public String showEditForm(@RequestParam("investmentId") Long investmentId, Model model) {
    // Fetch the investment by its ID
    Investment investment = investmentService.getInvestmentById(investmentId);

    if (investment != null) {
        model.addAttribute("investment", investment);
        return "myAdmin/editInvestment"; // This is the page with the edit form
    }
    return "redirect:/myAdmin/adminListInvest"; // Redirect if the investment is not found
}

@PostMapping("/admin/investments/edit")
public String editInvestment(@RequestParam("investmentId") Long investmentId,
                              @RequestParam("name") String name,
                              @RequestParam("riskRating") String riskRating,
                              @RequestParam("minInvestment") Double minInvestment,
                              RedirectAttributes redirectAttributes) {
    // Fetch the investment from the database
    Investment investment = investmentService.getInvestmentById(investmentId);

    if (investment != null) {
        // Update the fields
        investment.setName(name);
        investment.setRiskRating(riskRating);
        investment.setMinInvestment(minInvestment);

        // Save the updated investment
        investmentService.saveInvestment(investment);

        // Add success message as a flash attribute
        redirectAttributes.addFlashAttribute("message", "Investment updated successfully!");
    }

    // Redirect to the investments list page
    return "redirect:/myAdmin/adminListInvest";
}

@GetMapping("/myAdmin/adminUser")
public String adminUser(Model model) {

    List<User> users = userService.getAllUsers();
    model.addAttribute("users", users);
    return "myAdmin/adminUser";
}
    // Method to deactivate (delete) a user
    @PostMapping("/admin/deactivateUser/{userId}")
    public String deactivateUser(@PathVariable Long userId, Model model) {
        userService.deleteUserById(userId); // Call service to delete user
        model.addAttribute("message", "user deleted successfully");
        return "redirect:/myAdmin/adminUser"; // Redirect back to the users list page
    }

}
