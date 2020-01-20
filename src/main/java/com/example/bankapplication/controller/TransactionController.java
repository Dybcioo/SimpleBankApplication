package com.example.bankapplication.controller;

import com.example.bankapplication.filter.TransactionFilter;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value="/transactionList.html", params = "id", method = {RequestMethod.GET, RequestMethod.POST})
    public String showVehicleList(Model model, Pageable pageable, @Valid @ModelAttribute("searchCommand") TransactionFilter search, BindingResult result, Long id){
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
}
