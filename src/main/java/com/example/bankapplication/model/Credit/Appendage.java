package com.example.bankapplication.model.Credit;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Appendage{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }
}
