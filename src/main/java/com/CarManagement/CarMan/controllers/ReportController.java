package com.CarManagement.CarMan.controllers;

import com.CarManagement.CarMan.helpers.EmailData;
import com.CarManagement.CarMan.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;



//    @GetMapping("/emailForm")
//    public String showEForm(Model model) {
//        model.addAttribute("emailData", new EmailData());
//        return "emailForm";
//    }



    @GetMapping("/emailForm")
    public String showEmailForm(Model model) {
        model.addAttribute("emailData", new EmailData());
        return "emailForm";
    }


    @PostMapping("/schedule")
    public String sendReport(@ModelAttribute("emailData") EmailData emailData, Principal principal) {
        reportService.sendReport(emailData.getEmail(), principal.getName());
        return "redirect:/home"; // Redirect to a suitable page after sending the report
    }
}

