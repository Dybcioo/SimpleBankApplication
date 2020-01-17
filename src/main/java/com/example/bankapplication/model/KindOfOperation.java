package com.example.bankapplication.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KindOfOperation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kind_operation")
    private Long id;
    @OneToMany(mappedBy = "kindOfOperation", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();
    @Column(name = "operation_kind")
    private String kind;

    public KindOfOperation(String kind) {
        this.kind = kind;
    }

    public Set<String> getTransactionsId(){
        Set<String> pom = new HashSet<>();
        if(!transactions.isEmpty()) {
            transactions.forEach(transaction -> pom.add(transaction.getId().toString()));
        }
        return  pom;
    }

    public void addTransaction(Transaction transaction){
        transaction.setKindOfOperation(this);
        getTransactions().add(transaction);
    }

    @Override
    public String toString() {
        return "KindOfOperation{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                '}';
    }
}
