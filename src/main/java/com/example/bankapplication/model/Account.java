package com.example.bankapplication.model;

import com.example.bankapplication.model.Credit.Credit;
import com.example.bankapplication.model.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,  orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Credit> credits = new HashSet<>();
    @Column(nullable = false)
    private String accountNumber;
    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(nullable = false)
    private BigDecimal balance;

    public Account(String accountNumber, BigDecimal balance, Date creationDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.creationDate = creationDate;
    }

    public void addTransaction(Transaction transaction){
        transaction.setAccount(this);
        getTransactions().add(transaction);
    }

    public void addCredit(Credit credit){
        credit.setAccount(this);
        getCredits().add(credit);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", transactions=" + transactions +
                //", credit=" + credit +
                ", accountNumber='" + accountNumber + '\'' +
                ", creationDate=" + creationDate +
                ", balance=" + balance +
                '}';
    }

    @PreRemove
    public void removeAll(){
        user.getAccounts().remove(this);
        transactions.forEach(transaction -> transaction.setAccount(null));
        credits.forEach(credit -> credit.setAccount(null));
    }
}
