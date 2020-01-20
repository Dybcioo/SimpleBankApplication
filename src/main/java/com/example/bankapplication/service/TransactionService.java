package com.example.bankapplication.service;

import com.example.bankapplication.filter.TransactionFilter;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Page<Transaction> getAllTransactions(TransactionFilter search, Pageable pageable, long id) {

        Page page;
        if(search.isEmpty()){
            page = transactionRepository.findAllByAccount_Id(id,pageable);
        }else{

            page = transactionRepository.findAllTransactionsUsingFilter(search.getPhraseLIKE(), search.getKind(), search.getMinAmount(), search.getMaxAmount(), search.getMinDate(), search.getMaxDate(), pageable, id);
        }
        return page;

    }
}
