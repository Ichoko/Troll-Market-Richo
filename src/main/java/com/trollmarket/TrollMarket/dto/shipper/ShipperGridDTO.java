package com.trollmarket.TrollMarket.dto.shipper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShipperGridDTO {
    private Long id;
    private String name;
    private Double price;
    private String service;
//    private Long total;

}
