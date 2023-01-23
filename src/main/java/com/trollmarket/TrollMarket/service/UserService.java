package com.trollmarket.TrollMarket.service;

import com.trollmarket.TrollMarket.dao.AccountRepository;
import com.trollmarket.TrollMarket.dao.BuyerRepository;
import com.trollmarket.TrollMarket.dao.SellerRepository;
import com.trollmarket.TrollMarket.dto.profile.ProfileHeaderDTO;
import com.trollmarket.TrollMarket.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private AccountRepository accountRepository;

    private String getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public String getAccountRole (String username){
        Account entity = accountRepository.findById(username).get();
        return entity.getRole();
    }


    public ProfileHeaderDTO getOneUserBuyer(String userName){
        var entity = buyerRepository.findById(userName).get();
        ProfileHeaderDTO dto = new ProfileHeaderDTO(
                entity.getUsername(),
                entity.getName(),
                null,
                entity.getAddress(),
                entity.getBalance()

        );
        return dto;
    }

    public ProfileHeaderDTO getOneUserSeller(String userName){
        var entity = sellerRepository.findById(userName).get();
        ProfileHeaderDTO dto = new ProfileHeaderDTO(
                entity.getUsername(),
                entity.getName(),
                null,
                entity.getAddress(),
                entity.getBalance()

        );
        return dto;
    }
}
