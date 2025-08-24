package com.invest.khumbu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invest.khumbu.Model.User;
import com.invest.khumbu.Repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Handle login
    @PostMapping("/use/login")
    public String loginUser(
            @RequestParam("emailMobile") String emailMobile,
            @RequestParam("password") String password,
            Model model) {

        User user = userRepository.findByEmailMobileAndPassword(emailMobile, password);

        if (user != null) {
            return "redirect:/index"; // Successful login
        }

        // Pass error to the same login page
        model.addAttribute("loginError", "Invalid email or password. Please try again.");
        return "logon/login"; // Render login.html with error
    }
}






