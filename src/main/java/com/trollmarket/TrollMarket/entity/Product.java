package com.trollmarket.TrollMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name="Product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SellerId")
    private String sellerId;

    @ManyToOne
    @JoinColumn(name = "SellerId" , insertable = false, updatable = false)
    private Seller seller;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "CategoryName")
    @NotNull
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Discontinue")
    private Boolean discontinue;

    public Product(Long id,
                   String sellerId,
                   String name,
                   String categoryName,
                   String description,
                   Double price,
                   Boolean discontinue) {
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
        this.discontinue = discontinue;
    }
}

