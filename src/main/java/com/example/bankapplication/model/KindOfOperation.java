package com.example.bankapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class KindOfOperation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kind_operation")
    private Long id;
    @OneToOne(mappedBy = "kindOfOperation")
    private Transaction transaction;
    @Column(name = "operation_kind")
    private String kind;

    public KindOfOperation(String kind) {
        this.kind = kind;
    }
}
