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
public class Target implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_target")
    private Long id;
    @OneToMany(mappedBy = "target", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Credit> credits = new HashSet<>();
    @Column(name = "target_name")
    private String name;

    public Target(String name) {
        this.name = name;
    }

    public void addCredit(Credit credit){
        credit.setTarget(this);
        getCredits().add(credit);
    }
    @Override
    public String toString() {
        return "Target{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
