package com.example.bankapplication.model.Credit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Credit> credits = new HashSet<>();
    @Column(name = "status_name")
    private String name;

    public Status(String name) {
        this.name = name;
    }

    public void addCredit(Credit credit){
        credit.setStatus(this);
        getCredits().add(credit);
    }
    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
