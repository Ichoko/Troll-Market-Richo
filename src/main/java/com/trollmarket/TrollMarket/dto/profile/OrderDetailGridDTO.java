package com.trollmarket.TrollMarket.dto.profile;

import com.trollmarket.TrollMarket.entity.Product;
import com.trollmarket.TrollMarket.entity.Shipper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailGridDTO {
    private Long id;
    private LocalDate orderDate;
    private Long productId;
    private String shipperId;
    private Integer quantity;
    private Double subPrice;
}
