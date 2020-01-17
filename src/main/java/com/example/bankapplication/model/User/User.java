package com.example.bankapplication.model.User;

import com.example.bankapplication.model.Account;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id_user")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id_role")})
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL)
    private Set<Account> accounts = new HashSet<>();
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, name = "birthday_date")
    private Date birthdayDate;
    @Column(nullable = false)
    private String gender;
    private String email;

    public User(String login, String password, String name, String lastName, Date birthdayDate, String gender, String email) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", address=" + address +
                ", role=" + roles +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", accounts='" + accounts + '\'' +
                '}';
    }

    public void addRole(Role role){
        getRoles().add(role);
    }

    public void addAccount(Account account){
        account.setUser(this);
        getAccounts().add(account);
    }
}
