package com.example.bankapplication.model.Credit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Target implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_target")
    private Long id;
    @OneToOne(mappedBy = "target")
    private Credit credit;
    @Column(name = "target_name")
    private String name;

    public Target(String name) {
        this.name = name;
    }
}
