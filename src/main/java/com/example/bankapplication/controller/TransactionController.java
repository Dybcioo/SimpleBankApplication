package com.example.bankapplication.controller;

import com.example.bankapplication.filter.TransactionFilter;
import com.example.bankapplication.model.KindOfOperation;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.repository.KindOfOperationRepository;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.repository.userRepo.UserRepository;
import com.example.bankapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    KindOfOperationRepository kindOfOperationRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value="/transactionList.html", params = "id", method = {RequestMethod.GET, RequestMethod.POST})
    public String showTransactionHistoryList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") TransactionFilter search, BindingResult result, Long id){
        if(id != null){
            search.setId(id);
        }
        model.addAttribute("transactionListPage", transactionService.getAllTransactions(search, pageable, search.getId()));
        return "transactionList";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "minDate", dateEditor);
        binder.registerCustomEditor(Date.class, "maxDate", dateEditor);

    }

    @GetMapping(value = "/transaction")
    public String transaction(Model model){
        Transaction transaction = new Transaction();
        transaction.setKindOfOperation(new KindOfOperation());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName());
        model.addAttribute("transaction", transaction);
        model.addAttribute("kinds", kindOfOperationRepository.findAll());
        model.addAttribute("accounts", user.getAccounts());
        return "transaction";
    }

    @PostMapping(value = "/transaction")
    public String doTransaction(Model model, @Valid @ModelAttribute("transaction") Transaction tran){
        transactionService.doTransaction(tran);
        return "redirect:/success";
    }
}
