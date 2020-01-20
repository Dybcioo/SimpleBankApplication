package com.example.bankapplication.service;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.repository.creditRepo.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CreditRepository creditRepository;

    public List<Account> getAll(){

        List<Account> page =  accountRepository.findAll();
        return page;
    }

    public List<Account> getAllUserAccounts(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return  accountRepository.findAllByUserLogin(auth.getName());
    }

    @Transactional
    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

    public Account getAccountById(Long id){
        Optional<Account> acc = accountRepository.findById(id);
        Account account = acc.orElse(new Account());
        account.getCredits().size();
        account.getTransactions().size();
        return account;
    }

    public void createAccount(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Account account = new Account(getRandomBankNumber(), new BigDecimal(0), new Date());
        account.setUser(userService.findUserByUserName(auth.getName()));
        accountRepository.save(account);
    }

    public String getRandomBankNumber(){
        Random random = new Random();
        String number = "1040";
        for(int i=0;i<22;i++){
            number+= String.valueOf(random.nextInt(10));
        }
        return number;
    }
}
