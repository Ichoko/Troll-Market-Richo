package com.trollmarket.TrollMarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGridDTO {
    private Long id;

    private String sellerId;

    @NotBlank(message = "name is required!")
    private String name;

    @NotBlank(message = "category is required!")
    private String category;

    private String description;

    @NotNull(message = "price is required!")
    private Double price;

    private Boolean discontinue;
}
