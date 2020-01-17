package com.example.bankapplication.repository.creditRepo;

import com.example.bankapplication.model.Credit.Appendage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppendageRepository extends JpaRepository<Appendage, Long> {
}
