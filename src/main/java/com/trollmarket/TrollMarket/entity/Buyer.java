package com.trollmarket.TrollMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name="Buyer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Buyer {
    @Id
    @Column(name="Username")
    private String username;

    @Column(name="Name")
    @NotNull
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Balance")
    private Double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Username")
    private Account account;
}
