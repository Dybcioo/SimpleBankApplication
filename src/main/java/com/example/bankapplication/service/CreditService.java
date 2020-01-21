package com.example.bankapplication.service;

import com.example.bankapplication.model.Credit.Appendage;
import com.example.bankapplication.model.Credit.Proposal;
import com.example.bankapplication.repository.creditRepo.AppendageRepository;
import com.example.bankapplication.repository.creditRepo.CreditRepository;
import com.example.bankapplication.repository.creditRepo.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    @Autowired
    CreditRepository creditRepository;
    @Autowired
    ProposalRepository proposalRepository;
    @Autowired
    AppendageRepository appendageRepository;


    public void addProposal(Proposal proposal){
        Proposal saveProposal = proposalRepository.save(proposal);
        if(!(proposal.getFile1().getPath() == null || proposal.getFile1().getPath().equalsIgnoreCase("")) ) {
            Appendage appendage1 = new Appendage(proposal.getFile1().getAbsolutePath());
            appendage1.setProposal(saveProposal);
            appendageRepository.save(appendage1);
        }
        if(!(proposal.getFile2().getPath() == null || proposal.getFile2().getPath().equalsIgnoreCase("")) ) {
            Appendage appendage2 = new Appendage(proposal.getFile2().getAbsolutePath());
            appendage2.setProposal(saveProposal);
            appendageRepository.save(appendage2);
        }
        if(!(proposal.getFile3().getPath() == null || proposal.getFile3().getPath().equalsIgnoreCase("")) ) {
            Appendage appendage3 = new Appendage(proposal.getFile3().getAbsolutePath());
            appendage3.setProposal(saveProposal);
            appendageRepository.save(appendage3);
        }
    }
}
