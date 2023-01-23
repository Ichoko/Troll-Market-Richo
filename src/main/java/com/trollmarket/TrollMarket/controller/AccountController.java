package com.trollmarket.TrollMarket.controller;

import com.trollmarket.TrollMarket.dto.DropDownDTO;
import com.trollmarket.TrollMarket.dto.account.RegisterAdminDTO;
import com.trollmarket.TrollMarket.dto.account.RegisterDTO;
import com.trollmarket.TrollMarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/loginForm")
    public String loginForm(Model model){
        List<DropDownDTO> role = DropDownDTO.getRoleDropdown();
        model.addAttribute("role",role);
        return "account/login-form";
    }

//  post mapping untuk login akan lewat spring

    @GetMapping ("/registerForm")
    public String registerForm (@RequestParam String role, Model model){
        if(role.equals("buyer")) {
            RegisterDTO buyer = new RegisterDTO();
            buyer.setRole("buyer");
            model.addAttribute("role",role);
            model.addAttribute("dto", buyer);
            return "account/register-form";
        }else if(role.equals("seller")){
            RegisterDTO seller = new RegisterDTO();
            seller.setRole("seller");
            model.addAttribute("role",role);
            model.addAttribute("dto", seller);
            return "account/register-form";
        }else {
            RegisterAdminDTO admin = new RegisterAdminDTO();
            admin.setRole("admin");
            model.addAttribute("role",role);
            model.addAttribute("dto", admin);
            return "account/register-form-admin";
        }
    }


    @PostMapping("/register")
    public String register (@Valid @ModelAttribute("dto") RegisterDTO registerDTO, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("role",registerDTO.getRole());
            return "account/register-form";
        }
        if (registerDTO.getRole().equals("buyer")){
            accountService.saveBuyer(registerDTO);
        }else {
            accountService.saveSeller(registerDTO);
        }
        return "redirect:/account/loginForm";
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin (@Valid @ModelAttribute("dto") RegisterAdminDTO registerAdminDTO, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("role", "admin");
            return "account/register-form-admin";
        }
        accountService.registerAccount(registerAdminDTO);
        RegisterAdminDTO adminDTO = new RegisterAdminDTO();
        adminDTO.setRole("admin");
        model.addAttribute("dto",adminDTO);
        model.addAttribute("role",adminDTO.getRole());
        return "redirect:/account/loginForm";
    }

    @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(Model model){
        return "account/access-denied";
    }

}
