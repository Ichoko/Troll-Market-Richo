package com.trollmarket.TrollMarket.validator;

import com.trollmarket.TrollMarket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator  <UniqueUsername, String>{
    @Autowired
    private AccountService service;
    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Boolean isExist = service.checkExistingUsername(value);
        return isExist;
    }
}
