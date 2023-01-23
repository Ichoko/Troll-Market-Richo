package com.trollmarket.TrollMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DropDownDTO {
    private Object value;
    private String text ;


    public static List<DropDownDTO> getRoleDropdown(){
        List<DropDownDTO> roles = new LinkedList<DropDownDTO>();
        roles.add(new DropDownDTO("seller","seller"));
        roles.add(new DropDownDTO("buyer","buyer"));
//        roles.add(new DropDownDTO("admin","admin"));
        return roles;
    }
}
