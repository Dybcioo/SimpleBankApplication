package com.example.bankapplication.controller;

import com.example.bankapplication.model.Credit.Credit;
import com.example.bankapplication.model.Credit.Proposal;
import com.example.bankapplication.model.Credit.Target;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.repository.creditRepo.ProposalRepository;
import com.example.bankapplication.repository.creditRepo.TargetRepository;
import com.example.bankapplication.repository.userRepo.UserRepository;
import com.example.bankapplication.service.CreditService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CreditController {

    @Autowired
    CreditService creditService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TargetRepository targetRepository;
    private static String UPLOADED_FOLDER = "C:\\Users\\domin\\IdeaProjects\\bank-application\\zal\\";

    @GetMapping(value = "/credit")
    public String credit(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Proposal proposal = new Proposal();
        Credit c = new Credit();
        c.setTarget(new Target());
        proposal.setCredit(c);
        User user = userRepository.findByLogin(auth.getName());
        model.addAttribute("accounts", user.getAccounts());
        model.addAttribute("targets", targetRepository.findAll());
        model.addAttribute("proposal", proposal);
        return "proposal";
    }

    @PostMapping(value = "/credit")
    public String addCredit(Model model, @Valid @ModelAttribute("proposal") Proposal proposal) throws IOException {
        if(proposal.getFile1().isEmpty()){
            System.out.println("----------------------------------------------------------------- F I L E 1 1 1 ");
        }else {
            byte[] bytes = proposal.getFile1().getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + proposal.getFile1().getOriginalFilename());
            Files.write(path, bytes);
        }
        if(proposal.getFile2().isEmpty()){
            System.out.println("----------------------------------------------------------------- F I L E 2 2 2");
        }else {
            byte[] bytes = proposal.getFile2().getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + proposal.getFile2().getOriginalFilename());
            Files.write(path, bytes);
        }
        if(proposal.getFile3().isEmpty()){
            System.out.println("----------------------------------------------------------------- F I L E 3 3  3");
        }else {
            byte[] bytes = proposal.getFile3().getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + proposal.getFile3().getOriginalFilename());
            Files.write(path, bytes);
        }

        creditService.addProposal(proposal);
        return "redirect:/success";
    }

    @GetMapping(value = "/checkProposal")
    public String getProposal(Model model) {
        model.addAttribute("proposals", creditService.getCheckProposal());
        return "checkProposal";
    }
    @GetMapping(value = "/proposal/accept", params = "id")
    public String acceptProposal(Long id){
        creditService.acceptProposal(id);
        return "redirect:/checkProposal";
    }
    @GetMapping(value = "/proposal/discard", params = "id")
    public String discardProposal(Long id){
        creditService.discardProposal(id);
        return "redirect:/checkProposal";
    }
    @GetMapping(value = "/proposal.html", params = "id")
    public String proposalDetails(Model model, Long id){
        model.addAttribute("proposal", creditService.getProposal(id));
        return "proposalDetails";
    }

}
