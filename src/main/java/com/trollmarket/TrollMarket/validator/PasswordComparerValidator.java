package com.trollmarket.TrollMarket.validator;

import com.trollmarket.TrollMarket.dto.account.RegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordComparerValidator implements ConstraintValidator<PasswordComparer, RegisterDTO> {
    @Override
    public void initialize(PasswordComparer constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
}
    @Override
    public boolean isValid(RegisterDTO value, ConstraintValidatorContext context){
        return value.getPassword().equals(value.getConfirmPassword());
    }
}
