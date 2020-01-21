package com.example.bankapplication.service;

import com.example.bankapplication.filter.TransactionFilter;
import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.KindOfOperation;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.KindOfOperationRepository;
import com.example.bankapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    KindOfOperationRepository kindOfOperationRepository;

    public Page<Transaction> getAllTransactions(TransactionFilter search, Pageable pageable, long id) {

        Page page;
        if(search.isEmpty()){
            page = transactionRepository.findAllByAccount_Id(id,pageable);
        }else{

            page = transactionRepository.findAllTransactionsUsingFilter(search.getPhraseLIKE(), search.getKind(), search.getMinAmount(), search.getMaxAmount(), search.getMinDate(), search.getMaxDate(), pageable, id);
        }
        return page;
    }

    public void doTransaction(Transaction tran){
        KindOfOperation kind = kindOfOperationRepository.findFirstByKind(tran.getKindOfOperation().getKind());
        if(kind.getKind().equalsIgnoreCase("przelew")){
          Account account = accountRepository.findFirstByAccountNumber(tran.getOptionAccountNumber());
          account.riseBalanse(tran.getAmount());
            someMethod(tran, kind, account, tran.getFromAccountNumber());

            Account account1 = accountRepository.findFirstByAccountNumber(tran.getFromAccountNumber());
            account1.reduceBalanse(tran.getAmount());
            someMethod(tran, kind, account1, tran.getOptionAccountNumber());

        }else if(kind.getKind().equalsIgnoreCase("wplata")){
            Account account1 = accountRepository.findFirstByAccountNumber(tran.getFromAccountNumber());
            account1.riseBalanse(tran.getAmount());
            someMethod(tran, kind, account1, "Wpłata gotówki");

        }else if(kind.getKind().equalsIgnoreCase("wyplata")){
            Account account1 = accountRepository.findFirstByAccountNumber(tran.getFromAccountNumber());
            account1.reduceBalanse(tran.getAmount());
            someMethod(tran, kind, account1, tran.getOptionAccountNumber());
        }
    }

    private void someMethod(Transaction tran, KindOfOperation kind, Account account1, String optionAccountNumber) {
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(tran.getAmount());
        transaction1.setText(tran.getText());
        transaction1.setDate(new Date());
        transaction1.setOptionAccountNumber(optionAccountNumber);
        transaction1.setKindOfOperation(kind);
        transaction1.setAccount(account1);

        transactionRepository.save(transaction1);
    }
}
