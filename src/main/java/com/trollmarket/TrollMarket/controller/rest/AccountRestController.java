package com.trollmarket.TrollMarket.controller.rest;

import com.trollmarket.TrollMarket.dto.account.RegisterDTO;
import com.trollmarket.TrollMarket.entity.Account;
import com.trollmarket.TrollMarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/account")
public class AccountRestController {
    @Autowired
    public AccountService accountService;
    @PostMapping("/addSaller")
    public ResponseEntity<Object> insertAccount (@RequestBody RegisterDTO registerDTO){
        var newSeller = accountService.saveSeller(registerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newSeller);
    }
}
