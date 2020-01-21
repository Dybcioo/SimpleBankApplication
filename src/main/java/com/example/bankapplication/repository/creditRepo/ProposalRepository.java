package com.example.bankapplication.repository.creditRepo;

import com.example.bankapplication.model.Credit.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findAllByStatusName(String status);
}
