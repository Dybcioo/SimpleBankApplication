package com.example.bankapplication.model.User;

import com.example.bankapplication.model.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, name = "birthday_date")
    private Calendar birthdayDate;
    @Column(nullable = false)
    private String gender;
    private String email;

    public User(String name, String password, String lastName, Calendar birthdayDate, String gender, String email) {
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.email = email;
    }
}
