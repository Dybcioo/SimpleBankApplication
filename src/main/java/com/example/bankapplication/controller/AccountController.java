package com.example.bankapplication.controller;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/account.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showAccountList(Model model){
        model.addAttribute("accountList", accountService.getAllUserAccounts());
        return "account";
    }

    @RequestMapping(value="/account.html", params = "id", method = RequestMethod.GET)
    public String showAccountDetails(Model model, long id){
       model.addAttribute("account", accountService.getAccountById(id));
        return "accountDetails";
    }

    @GetMapping(path="/account.html", params={"did"})
    public String deleteAccount(long did){
        accountService.deleteAccount(did);
        return "redirect:/account.html";

    }
    @GetMapping(path="/account/add")
    public String addAccount(Model model){
        accountService.createAccount();
        return "redirect:/account.html";
    }

}
