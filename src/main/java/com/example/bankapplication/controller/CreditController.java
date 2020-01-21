package com.example.bankapplication.controller;

import com.example.bankapplication.model.Credit.Proposal;
import com.example.bankapplication.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping(value = "/credit")
    public String credit(Model model){
        model.addAttribute("proposal", new Proposal());
        return "proposal";
    }

    @PostMapping(value = "/credit")
    public String addCredit(Model model, @Valid @ModelAttribute("proposal") Proposal proposal){
        creditService.addProposal(proposal);
        return "redirect:/success";
    }
}
