package com.example.bankapplication.repository;

import com.example.bankapplication.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByAccount_Id(long id, Pageable pageable);

    Page<Transaction> findByAccount_AccountNumberContaining(String phrase, Pageable pageable);

    @Query("SELECT t FROM Transaction t " +
            "INNER JOIN t.account a " +
            " INNER JOIN t.kindOfOperation k WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(a.accountNumber) LIKE upper(:phrase) " +
            ") AND " +
            "(" +
            ":kind is null OR :kind = '' OR "+
            "upper(k.kind) LIKE upper(:kind) " +
            ") " +
            "AND " +
            "(:min is null OR :min <= t.amount) " +
            "AND (:max is null OR :max >= t.amount) AND" +
            " (:minDate is null OR :minDate <= t.date) " +
            "AND (:maxDate is null OR :maxDate >= t.date) AND a.id = :id")
    Page<Transaction> findAllTransactionsUsingFilter(@Param("phrase") String p, @Param("kind") String kind, @Param("min") BigDecimal amountMin, @Param("max") BigDecimal amountMax, @Param("minDate") Date dateMin, @Param("maxDate") Date dateMax, Pageable pageable, @Param("id") long id);
}
