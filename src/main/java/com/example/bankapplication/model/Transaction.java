package com.example.bankapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "kind_operation_id")
    private KindOfOperation kindOfOperation;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    private Account account;
    private String text;
    private BigDecimal amount;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Transaction(BigDecimal amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", kindOfOperation" + kindOfOperation +
                ", accountId=" + account.getId() +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
