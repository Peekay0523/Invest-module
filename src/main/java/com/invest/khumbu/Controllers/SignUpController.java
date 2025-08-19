package com.invest.khumbu.Controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.invest.khumbu.Model.User;
import com.invest.khumbu.Repository.UserRepository;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/register")
    public String registerUser(
            @RequestParam("email_mobile") String emailMobile,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            // redirect to form with error message
            return "redirect:/register?error=password_mismatch";
        }

        User user = new User();
        user.setEmail(emailMobile);
        user.setName(username);
        user.setPassword(password);
        user.setDate(new Date()); // automatically set today’s date

        userRepository.save(user);

        return "redirect:/logon/login"; // after success
    }

    @PostMapping("/users/login")
        public String loginUser(
        //@RequestParam("email_mobile") String emailMobile,
        @RequestParam("password") String password) {

    //User user = userRepository.findByEmailMobileAndPassword(emailMobile, password);

    if (password.equals("1111")) {
        return "redirect:/index"; // normal client homepage
    }

    return "redirect:/logon/login?error=true";
}
}
