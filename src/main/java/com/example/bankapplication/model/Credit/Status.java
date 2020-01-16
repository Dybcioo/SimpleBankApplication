package com.example.bankapplication.model.Credit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;
    @OneToOne(mappedBy = "status")
    private Credit credit;
    @Column(name = "status_name")
    private String name;

    public Status(String name) {
        this.name = name;
    }
}
