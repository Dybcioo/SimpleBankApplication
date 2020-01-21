package com.example.bankapplication.service;

import com.example.bankapplication.model.Credit.Appendage;
import com.example.bankapplication.model.Credit.Credit;
import com.example.bankapplication.model.Credit.Proposal;
import com.example.bankapplication.model.Credit.Target;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.creditRepo.*;
import com.example.bankapplication.repository.userRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CreditService {

    @Autowired
    CreditRepository creditRepository;
    @Autowired
    ProposalRepository proposalRepository;
    @Autowired
    AppendageRepository appendageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TargetRepository targetRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    AccountRepository accountRepository;


    public void addProposal(Proposal proposal){
        Credit credit = new Credit();
        credit.setTarget(targetRepository.findByName(proposal.getCredit().getTarget().getName()));
        credit.setAccount(accountRepository.findFirstByAccountNumber(proposal.getCredit().getAccount().getAccountNumber()));
        credit.setLoanAmount(proposal.getCredit().getLoanAmount());
        proposal.setStatus(statusRepository.findByName("Wniosek przyjety do rozpatrzenia"));
        proposal.setCredit(null);
        Proposal saveProposal = proposalRepository.save(proposal);
        if(!(proposal.getFile1().getPath() == null || proposal.getFile1().getPath().equalsIgnoreCase("")) ) {
            Appendage appendage1 = new Appendage("C:\\Users\\domin\\IdeaProjects\\bank-application\\zal\\"+proposal.getFile1().getPath());
            appendage1.setProposal(saveProposal);
            appendageRepository.save(appendage1);
        }
        if(!(proposal.getFile2().getPath() == null || proposal.getFile2().getPath().equalsIgnoreCase("")) ) {
            Appendage appendage2 = new Appendage("C:\\Users\\domin\\IdeaProjects\\bank-application\\zal\\"+proposal.getFile2().getPath());
            appendage2.setProposal(saveProposal);
            appendageRepository.save(appendage2);
        }
        if(!(proposal.getFile3().getPath() == null || proposal.getFile3().getPath().equalsIgnoreCase("")) ) {
            Appendage appendage3 = new Appendage("C:\\Users\\domin\\IdeaProjects\\bank-application\\zal\\"+proposal.getFile3().getPath());
            appendage3.setProposal(saveProposal);
            appendageRepository.save(appendage3);
        }
        credit.setStartDate(new Date());
        credit.setProposal(saveProposal);
        creditRepository.save(credit);
    }
    public List<Proposal> getCheckProposal(){
        return proposalRepository.findAllByStatusName("Wniosek przyjety do rozpatrzenia");
    }

    public void acceptProposal(Long id){
        Proposal proposal = proposalRepository.findById(id).orElse(new Proposal());
        Credit credit = creditRepository.findById(proposal.getCredit().getId()).orElse(new Credit());
        credit.setInstallment(getInstallment(credit.getLoanAmount()));
        credit.setRepaymentAmount(getRepaymentAmount(credit.getLoanAmount()));
        credit.setStartDate(new Date());
        proposal.setStatus(statusRepository.findByName("Wniosek zaakceptowany"));
        proposal = proposalRepository.save(proposal);
        credit.setProposal(proposal);
        creditRepository.save(credit);
    }

    public BigDecimal getInstallment(BigDecimal amount){
        return amount.divide(new BigDecimal(60));
    }

    public BigDecimal getRepaymentAmount(BigDecimal amount){
        return amount.multiply(new BigDecimal(1.2));
    }

    public void discardProposal(Long id) {
        Proposal proposal = proposalRepository.findById(id).orElse(new Proposal());
        proposal.setStatus(statusRepository.findByName("Wniosek odrzucony"));
        proposal = proposalRepository.save(proposal);
        Credit credit = creditRepository.findById(proposal.getCredit().getId()).orElse(new Credit());
        credit.setInstallment(new BigDecimal(0));
        credit.setRepaymentAmount(new BigDecimal(0));
        credit.setLoanAmount(new BigDecimal(0));
        credit.setStartDate(new Date());
        credit.setProposal(proposal);
        creditRepository.save(credit);
    }

    public Proposal getProposal(Long id){
        return proposalRepository.findById(id).orElse(new Proposal());
    }
}
