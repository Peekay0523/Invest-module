package com.invest.khumbu.Controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invest.khumbu.Model.User;
import com.invest.khumbu.Repository.UserRepository;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    // Render the page
    @GetMapping("/signup")
    public String showSignup(
            @RequestParam(value = "email_mobile", required = false) String emailMobile,
            @RequestParam(value = "username", required = false) String username,
            Model model) {
        model.addAttribute("email_mobile", emailMobile);
        model.addAttribute("username", username);
        return "logon/signup";
    }

    @PostMapping("/users/register")
    public String registerUser(@RequestParam("email_mobile") String emailMobile,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model) {

        // keep entered values
        model.addAttribute("email_mobile", emailMobile);
        model.addAttribute("username", username);

        // ✅ FIXED: reject only if neither is valid (email NOR phone)
        if (!isValidEmail(emailMobile) && !isValidTenDigitPhone(emailMobile)) {
            model.addAttribute("errorMessage", "Enter a valid email address OR a 10-digit phone number");
            return "logon/signup";
        }

        // duplicate check
        if (userRepository.existsByEmailMobile(emailMobile)) {
            model.addAttribute("errorMessage", "Email or mobile already exists!");
            return "logon/signup";
        }

        // password match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match!");
            return "logon/signup";
        }

        // save user (align property name with your entity!)
        User user = new User();
        user.setEmail(emailMobile);    // <-- ensure your entity has this field
        user.setName(username);
        user.setPassword(password); 
        user.setDate(new Date());      // TODO: hash before saving
        userRepository.save(user);        
        model.addAttribute("successMessage", "Registration successful! You can now log in.");
        return "logon/signup";
    }

    private boolean isValidEmail(String s) {
        if (s == null) return false;
        return s.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private boolean isValidTenDigitPhone(String s) {
        if (s == null) return false;
        String digits = s.replaceAll("\\D", ""); // allow spaces/dashes, etc.
        return digits.length() == 10;
    }
}

