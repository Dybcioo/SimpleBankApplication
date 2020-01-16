package com.example.bankapplication.model.Credit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Appendage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appendage")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;
    @Column(nullable = false)
    private String path;

    public Appendage(String path) {
        this.path = path;
    }
}
