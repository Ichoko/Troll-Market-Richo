package com.trollmarket.TrollMarket.service;

import com.trollmarket.TrollMarket.ApplicationUserDetails;
import com.trollmarket.TrollMarket.dao.AccountRepository;
import com.trollmarket.TrollMarket.dao.BuyerRepository;
import com.trollmarket.TrollMarket.dao.SellerRepository;
import com.trollmarket.TrollMarket.dto.DropDownDTO;
import com.trollmarket.TrollMarket.dto.account.RegisterAdminDTO;
import com.trollmarket.TrollMarket.dto.account.RegisterDTO;
import com.trollmarket.TrollMarket.dto.profile.ProfileHeaderDTO;
import com.trollmarket.TrollMarket.entity.Account;
import com.trollmarket.TrollMarket.entity.Buyer;
import com.trollmarket.TrollMarket.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;

    // disini dijadikan acak2

    public void registerAccount(RegisterAdminDTO dto){
        String hasilEncript = passwordEncoder.encode(dto.getPassword());
        Account entity = new Account(
                dto.getUsername(),
                hasilEncript,
                dto.getRole()
        );
        accountRepository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account entity = accountRepository.findById(username).get();
        UserDetails userDetailsDTO = new ApplicationUserDetails(entity);
        return userDetailsDTO;
    }
    public Boolean checkExistingUsername(String username){
        Long totalUsername = accountRepository.countUserName(username);
        return totalUsername < 1;
    }

    public String getAccountRole (String username){
        Account entity = accountRepository.findById(username).get();
        return entity.getRole();
    }

    public List<DropDownDTO> getRoleDropdown() {
        return DropDownDTO.getRoleDropdown();
    }

    public void saveBuyer(RegisterDTO registerDTO) {
        String hashPassword = passwordEncoder.encode(registerDTO.getPassword());
        Account account = new Account(registerDTO.getUsername(),
                hashPassword,
                registerDTO.getRole());
        Buyer buyer = new Buyer(registerDTO.getUsername(),
                registerDTO.getName(),
                registerDTO.getAddress(),
                Double.valueOf(0),
                account);
        buyerRepository.save(buyer);
    }

    public Seller saveSeller(RegisterDTO registerDTO) {
        String hashPassword = passwordEncoder.encode(registerDTO.getPassword());
        Account account = new Account(
                registerDTO.getUsername(),
                hashPassword,
                registerDTO.getRole());
        Seller seller = new Seller(registerDTO.getUsername(), registerDTO.getName(),
                registerDTO.getAddress(),
                Double.valueOf(0),account);
       return sellerRepository.save(seller);
    }

}
