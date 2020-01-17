package com.example.bankapplication.model.Credit;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Proposal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proposal")
    private long id;
    @OneToMany(mappedBy = "proposal", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appendage> appendages = new HashSet<>();
    @OneToOne(mappedBy = "proposal", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private Credit credit;
    private BigDecimal income;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "mother_maiden_name")
    private String motherMaidenName;

    public void addAppendage(Appendage appendage){
        appendage.setProposal(this);
        getAppendages().add(appendage);
    }

    public Proposal(BigDecimal income, String fatherName, String motherName, String motherMaidenName) {
        this.income = income;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.motherMaidenName = motherMaidenName;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", creditId=" + credit.getId() +
                ", income=" + income +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", motherMaidenName='" + motherMaidenName + '\'' +
                '}';
    }
}
