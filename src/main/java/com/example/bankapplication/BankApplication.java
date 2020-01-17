package com.example.bankapplication;

import com.example.bankapplication.model.Account;
import com.example.bankapplication.model.Credit.Credit;
import com.example.bankapplication.model.Credit.Status;
import com.example.bankapplication.model.Credit.Target;
import com.example.bankapplication.model.KindOfOperation;
import com.example.bankapplication.model.Transaction;
import com.example.bankapplication.model.User.Address;
import com.example.bankapplication.model.User.Role;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.repository.AccountRepository;
import com.example.bankapplication.repository.creditRepo.CreditRepository;
import com.example.bankapplication.repository.creditRepo.StatusRepository;
import com.example.bankapplication.repository.creditRepo.TargetRepository;
import com.example.bankapplication.repository.userRepo.AddressRepository;
import com.example.bankapplication.repository.userRepo.RoleRepository;
import com.example.bankapplication.repository.userRepo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =  SpringApplication.run(BankApplication.class, args);
        Address address = new Address("Mazowieckie", "Warszawa", "Szkolna", "5");
        Role role = new Role("user");
        User user = new User("Wacek", "asdqwe", "Jan", "antoniak", new Date(1992,9,21), "male", "jacek@gmail.com");
        user.setAddress(address);

        RoleRepository roleRepo = ctx.getBean(RoleRepository.class);
        role.addUser(user);
        roleRepo.save(role);
        System.out.println(role);
        user.addRole(role);

        UserRepository userRepository = ctx.getBean(UserRepository.class);
        userRepository.save(user);
        Account account = new Account("12345678901234", new BigDecimal(3214.54), new Date(Calendar.DATE));
        AccountRepository accountRepository = ctx.getBean(AccountRepository.class);
        accountRepository.save(account);
        user =  userRepository.findById(1L).get();

        user.addAccount(account);
        userRepository.save(user);
        System.out.println(user);

        KindOfOperation kindOfOperation = new KindOfOperation("przelew");
        Transaction transaction = new Transaction(new BigDecimal(1200), new Date(Calendar.DATE));
        kindOfOperation.addTransaction(transaction);
        account.addTransaction(transaction);

        System.out.println(user);


        Status status = new Status("przyjete do realizacji");
        StatusRepository statRepo = ctx.getBean(StatusRepository.class);
        statRepo.save(status);

        Target target = new Target("hipoteczny");
        TargetRepository taRepo = ctx.getBean(TargetRepository.class);
        taRepo.save(target);

        Credit credit = new Credit(new Date(Calendar.DATE), new BigDecimal(123), new BigDecimal(2222), new BigDecimal(2231));
        credit.setStatus(status);
        credit.setTarget(target);
        credit.setAccount(account);
        CreditRepository credRepo = ctx.getBean(CreditRepository.class);
        credRepo.save(credit);

        System.out.println(credit);
        ctx.close();
    }

}
