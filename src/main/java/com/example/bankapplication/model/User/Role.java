package com.example.bankapplication.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;
    @OneToOne(mappedBy = "role")
    private User user;
    @Column(name = "role_name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
