package com.example.bankapplication.repository.creditRepo;

import com.example.bankapplication.model.Credit.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
}
