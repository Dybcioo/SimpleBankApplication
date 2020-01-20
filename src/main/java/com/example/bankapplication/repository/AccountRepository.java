package com.example.bankapplication.repository;

import com.example.bankapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserLogin(String login);
    Optional<Account> findById(Long id);
    Account findFirstByAccountNumber(String accountNumber);
}
