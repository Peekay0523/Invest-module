package com.invest.khumbu.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invest.khumbu.Model.User;
import com.invest.khumbu.Repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/use/login")
    public String loginUser(
            @RequestParam("emailMobile") String emailMobile,
            @RequestParam("password") String password) {

        // Directly query the database for matching user
        User user = userRepository.findByEmailMobileAndPassword(emailMobile, password);

        if (user != null) {
            // ✅ Successful login
            return "redirect:/index"; // normal client homepage
        }

        // ❌ Login failed
        return "redirect:/logon/login?error=true";
    }
}


