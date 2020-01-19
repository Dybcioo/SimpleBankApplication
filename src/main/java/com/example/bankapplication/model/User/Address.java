package com.example.bankapplication.model.User;

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
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;
    private String province;
    private String city;
    private String street;
    private String numberHouse;

    public Address(String province, String city, String street, String numberHouse) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.numberHouse = numberHouse;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberHouse='" + numberHouse + '\'' +
                '}';
    }
}
