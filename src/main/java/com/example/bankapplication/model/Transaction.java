package com.example.bankapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id;
    @OneToOne
    @JoinColumn(name = "kind_operation_id")
    private KindOfOperation kindOfOperation;
    @OneToOne(mappedBy = "transaction")
    private Account account;
    private BigDecimal amount;
    private Calendar date;

    public Transaction(BigDecimal amount, Calendar date) {
        this.amount = amount;
        this.date = date;
    }
}
