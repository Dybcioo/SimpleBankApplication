package com.example.bankapplication.repository;

import com.example.bankapplication.model.KindOfOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindOfOperationRepository extends JpaRepository<KindOfOperation, Long> {
}
