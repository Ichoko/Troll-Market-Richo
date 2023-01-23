package com.trollmarket.TrollMarket.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.trollmarket.TrollMarket.validator.PasswordComparer;
import com.trollmarket.TrollMarket.validator.UniqueUsername;

import javax.validation.constraints.NotBlank;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@PasswordComparer(message = "Konfirmasi Password harus Sama")
public class RegisterDTO {

    @UniqueUsername(message = "Maaf, nama sudah Digunakan")
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "password confirmation is required")
    private String confirmPassword;

    @NotBlank(message = "role is required")
    private String role;

    @NotBlank(message = "Name is required")
    private String name;

    private String address;

    private Double balance;
}
