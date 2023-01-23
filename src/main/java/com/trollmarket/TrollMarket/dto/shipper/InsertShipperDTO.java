package com.trollmarket.TrollMarket.dto.shipper;

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
public class InsertShipperDTO {
    private Long id;
    @NotBlank(message = "nama ekspedisi kosong!")
    private String name;
    @NotNull(message = "Masukkan Harga!")
    private Double price;
    private Boolean service;
}
