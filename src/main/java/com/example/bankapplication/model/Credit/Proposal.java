package com.example.bankapplication.model.Credit;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Proposal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proposal")
    private long id;
    @OneToMany(mappedBy = "proposal")
    List<Appendage> appendages;
    @OneToOne(mappedBy = "proposal")
    private Credit credit;
    private BigDecimal income;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "mother_maiden_name")
    private String motherMaidenName;

    public Proposal(BigDecimal income, String fatherName, String motherName, String motherMaidenName) {
        this.income = income;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.motherMaidenName = motherMaidenName;
    }
}
