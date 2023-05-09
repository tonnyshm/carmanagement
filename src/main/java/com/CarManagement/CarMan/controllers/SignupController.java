package com.CarManagement.CarMan.controllers;

import com.CarManagement.CarMan.model.User;
import com.CarManagement.CarMan.services.SignupService;
import com.CarManagement.CarMan.validators.UserValidator;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class SignupController {

    private final SignupService signupService;
    private final UserValidator userValidator;

    @Autowired
    public SignupController(SignupService signupService, UserValidator userValidator) {
        this.signupService = signupService;
        this.userValidator = userValidator;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }


    @PostMapping("/signup")
    public String processSignupForm(@ModelAttribute("user") @Valid User user, BindingResult result
                                    ) throws IOException {
       // userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signup";
        }
        signupService.saveUser(user);
        return "redirect:/login";
    }

}


