package com.trollmarket.TrollMarket.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileGridDTO {
    private String name;
    private String role;
    private String address;
    private Double balance;
}
