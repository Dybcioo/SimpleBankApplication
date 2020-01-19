package com.example.bankapplication.config;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.Credit.*;
import com.example.bankapplication.model.KindOfOperation;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.model.User.Address;
import com.example.bankapplication.model.User.Role;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.KindOfOperationRepository;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.repository.creditRepo.*;
import com.example.bankapplication.repository.userRepo.AddressRepository;
import com.example.bankapplication.repository.userRepo.RoleRepository;
import com.example.bankapplication.repository.userRepo.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private AppendageRepository appendageRepository;

    @Autowired
    private CreditRepository  creditRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KindOfOperationRepository kindOfOperationRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Account userAccount;
    private KindOfOperation koo;
    private Target target;
    private Status status;

    @Bean
    public InitializingBean init(){
        return () -> {
            if(userRepository.findAll().isEmpty()){
                try {
                    Role roleUser = roleRepository.save(new Role("USER"));
                    Role roleAdmin = roleRepository.save(new Role("ADMIN"));

                    User user = new User("user",passwordEncoder.encode("user"), "Jan", "Kowalik", new Date(87,10,22), "janKowalik@o2.pl");
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setActive(true);
                    Address a = new Address("Mazowieckie", "Warszawa", "Szkolna", "5");
                    user.setAddress(a);

                    User admin = new User("admin",passwordEncoder.encode("admin"), "Wacek", "Wackowski", new Date(75,2,1), "WacWac@o2.pl");
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setActive(true);
                    Address b = new Address("Opolskie", "Opole", "Opolska", "121");
                    admin.setAddress(b);

                    User test = new User("test",passwordEncoder.encode("test"), "Gosia", "Samosia", new Date(92,1,22), "gosiaSamosia@o2.pl");
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    Address c = new Address("Mazowieckie", "Warszawa", "Gliniana", "33");
                    test.setAddress(c);

                    addressRepository.save(a);
                    addressRepository.save(b);
                    addressRepository.save(c);

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);

                    userAccount = new Account("39124043575554328424913405", new BigDecimal(35_200), new Date());
                    userAccount.setUser(user);
                    Account adminAccount = new Account("78106000185855053759585506", new BigDecimal(120_440), new Date());
                    adminAccount.setUser(admin);
                    Account testAccount = new Account("57815900031710817428352500", new BigDecimal(53), new Date());
                    testAccount.setUser(test);

                    accountRepository.saveAndFlush(userAccount);
                    accountRepository.saveAndFlush(adminAccount);
                    accountRepository.saveAndFlush(testAccount);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(kindOfOperationRepository.findAll().isEmpty()){
                koo = new KindOfOperation("Przelew");
                kindOfOperationRepository.saveAndFlush(koo);
                kindOfOperationRepository.saveAndFlush(new KindOfOperation("Wplata"));
                kindOfOperationRepository.saveAndFlush(new KindOfOperation("Wyplata"));
            }
            if(transactionRepository.findAll().isEmpty()){
                Transaction transaction = new Transaction(new BigDecimal(1000), new Date());
                transaction.setKindOfOperation(koo);
                transaction.setAccount(userAccount);

                transactionRepository.save(transaction);
            }
            if(targetRepository.findAll().isEmpty()){
                target = new Target("Zakup nieruchomosci");
                targetRepository.saveAndFlush(target);
                targetRepository.saveAndFlush(new Target("Zakup ruchomosci"));
                targetRepository.saveAndFlush(new Target("Remont nieruchomosci"));
            }
            if(statusRepository.findAll().isEmpty()){
                status = new Status("Wniosek przyjety do rozpatrzenia");
                statusRepository.saveAndFlush(status);
                statusRepository.saveAndFlush(new Status("Trwa weryfikacja"));
                statusRepository.saveAndFlush(new Status("Wniosek zaakceptowany"));
                statusRepository.saveAndFlush(new Status("Wniosek odrzucony"));
            }
            if(creditRepository.findAll().isEmpty()){
                Credit credit = new Credit(new Date(), new BigDecimal(150_000), new BigDecimal(2100), new BigDecimal(200_000));
                credit.setTarget(target);
                credit.setStatus(status);
                Proposal proposal = new Proposal(new BigDecimal(3700), "Wladek", "Joanna", "Kowalska");
                Appendage appendage = new Appendage("/document/appendage1.doc");

                proposalRepository.saveAndFlush(proposal);
                appendage.setProposal(proposal);
                appendageRepository.saveAndFlush(appendage);


                credit.setProposal(proposal);
                creditRepository.saveAndFlush(credit);

            }
        };
    }
}
