package com.example.bankapplication.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;
    @OneToOne(mappedBy = "address")
    private User user;
    private String province;
    private String city;
    private String numberHouse;

    public Address(String province, String city, String numberHouse) {
        this.province = province;
        this.city = city;
        this.numberHouse = numberHouse;
    }
}
