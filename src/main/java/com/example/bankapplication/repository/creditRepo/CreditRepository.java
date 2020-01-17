package com.example.bankapplication.repository.creditRepo;

import com.example.bankapplication.model.Credit.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
}
