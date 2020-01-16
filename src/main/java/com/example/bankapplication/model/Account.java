package com.example.bankapplication.model;

import com.example.bankapplication.model.Credit.Credit;
import com.example.bankapplication.model.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @OneToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
    @Column(nullable = false)
    private String accountNumber;
    @Column(name = "creation_date")
    private Calendar creationDate;
    @Column(nullable = false)
    private BigDecimal balance;

    public Account(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
