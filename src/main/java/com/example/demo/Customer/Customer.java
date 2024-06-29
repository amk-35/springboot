package com.example.demo.Customer;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="customer")
@Data
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )


    private List<Address> addresses;


}
