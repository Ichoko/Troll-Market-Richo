package com.trollmarket.TrollMarket.controller;
import com.trollmarket.TrollMarket.dto.profile.ProfileHeaderDTO;
import com.trollmarket.TrollMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")


    public String index(String username, Model model){
        var role = userService.getAccountRole(username);
        ProfileHeaderDTO dto =new ProfileHeaderDTO();
        if(role.equalsIgnoreCase("buyer")){
            dto = userService.getOneUserBuyer(username);
        } else if (role.equalsIgnoreCase("seller")) {
            dto =userService.getOneUserSeller(username);
        }
        model.addAttribute("dto", dto);
        return "profile/profile-index";
    }
}
