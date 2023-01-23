package com.trollmarket.TrollMarket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "OrderDate")
    private LocalDate orderDate;

    @Column(name = "ProductId")
    private Long productId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ProductId",insertable = false, updatable = false)
    private Product product;


    @Column(name = "ShipperId")
    private String shipperId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ShipperId", insertable = false, updatable = false)
    private Shipper shipper;

    @Column(name = "Quantity", columnDefinition = "default 1")
    private Integer quantity;

    @Column(name = "SubPrice")
    private Double subPrice;

    public OrderDetail(Long id,
                       LocalDate orderDate,
                       Long productId,
                       String shipperId,
                       Integer quantity,
                       Double subPrice) {
        this.id = id;
        this.orderDate = orderDate;
        this.productId = productId;
        this.shipperId = shipperId;
        this.quantity = quantity;
        this.subPrice = subPrice;
    }
}
