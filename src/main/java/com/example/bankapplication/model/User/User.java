package com.example.bankapplication.model.User;

import com.example.bankapplication.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
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
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id_user")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id_role")})
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
    cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<>();
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 20)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, name = "birthday_date")
    @Past
    @Temporal(TemporalType.DATE)
    private Date birthdayDate;
    @Email
    private String email;
    private Boolean active;

    public User(String login, String password, String name, String lastName, Date birthdayDate, String email) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
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
