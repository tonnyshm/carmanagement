package com.CarManagement.CarMan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/login/success")
    public String showLoginSuccess(Model model) {
        model.addAttribute("message", "Authentication successful!");
        return "home";
    }

}
