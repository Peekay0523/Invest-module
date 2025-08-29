package com.invest.khumbu.Controllers;

import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.invest.khumbu.Model.User;
import com.invest.khumbu.Model.User.Language;
import com.invest.khumbu.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentService documentService;  

    // Render the page
    @GetMapping("/signup")
    public String showSignup(
            @RequestParam(value = "email_mobile", required = false) String emailMobile,
            @RequestParam(value = "fullname", required = false) String fullname,
            Model model) {
        model.addAttribute("email_mobile", emailMobile);
        model.addAttribute("fullname", fullname);
        
        return "logon/signup";
    }
/* 
        @PostMapping("/users/proceed")
    public String proceedUser(
            @RequestParam("fullname") String fullname,
            @RequestParam("idNumber") String idNumber,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("emailMobile") String emailMobile,
            @RequestParam("password") String password,
            @RequestParam("preferredLanguage") Language preferredLanguage,
            HttpSession session) {

        User user = new User();
        user.setName(fullname);
        user.setIdNumber(idNumber);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(emailMobile);
        user.setPassword(password);
        user.setPreferredLanguage(preferredLanguage);

        // Store temporary user in session (not saved yet)
        session.setAttribute("tempUser", user);

        // Redirect to documents upload page
        return "documents-signup";
    }
*/


    @PostMapping("/users/proceed")
    public String registerUser(@RequestParam("emailMobile") String emailMobile,
                               @RequestParam("fullname") String fullname,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("watsappNumber") String watsappNumber,
                               @RequestParam("idNumber") String idNumber,
                               @RequestParam("preferredLanguage") String preferredLanguage,
                               Model model,
                               HttpSession session) {

        // keep entered values
        model.addAttribute("email_mobile", emailMobile);
        model.addAttribute("fullname", fullname);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("watsappNumber", watsappNumber);
        model.addAttribute("idNumber", idNumber);
        model.addAttribute("preferredLanguage", preferredLanguage);
        // ✅ FIXED: reject only if neither is valid (email NOR phone)
        if (!isValidEmail(emailMobile) && !isValidTenDigitPhone(emailMobile)) {
            model.addAttribute("errorMessage", "Enter a valid email address OR a 10-digit phone number");
            model.addAttribute("languages", Language.values());
            return "logon/signup";
        }

        // duplicate check
        if (userRepository.existsByEmailMobile(emailMobile)) {
            model.addAttribute("errorMessage", "Email or mobile already exists!");
            model.addAttribute("languages", Language.values());
            return "logon/signup";
        }

        // password match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match!");
            model.addAttribute("languages", Language.values());
            return "logon/signup";
        }

        // save user (align property name with your entity!)
        User user = new User();
        user.setEmail(emailMobile);    // <-- ensure your entity has this field
        user.setName(fullname);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password); 
        user.setDate(new Date());
        user.setWatsappNumber(watsappNumber);  
        user.setIdNumber(idNumber); 
        user.setPreferredLanguage(User.Language.valueOf(preferredLanguage));   // TODO: hash before saving
        session.setAttribute("tempUser", user);  
        
        model.addAttribute("successMessage", "Registration successful! You can now log in.");
        return "logon/documents-signup";
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

    @PostMapping("/users/register")
public String registerUser(@RequestParam("idBackImageUrl") MultipartFile idBack,
                           @RequestParam("idFrontImageUrl") MultipartFile idFront,
                           @RequestParam("proofOfAddress") MultipartFile proofOfAddr,
                           HttpSession session

)throws IOException {

    User user = (User) session.getAttribute("tempUser");
    if (user == null)
    return "redirect:/logon/signup";

    String idBackPath = documentService.save(idBack, user);
    String idFrontPath = documentService.save(idFront, user);

    user.setIdBackImageUrl(idBackPath);
    user.setIdFrontImageUrl(idFrontPath);
    user.setProofOfAddress(proofOfAddr.getBytes());

    userRepository.save(user);
    session.removeAttribute("tempUser");
    return "logon/signup";
}

}

