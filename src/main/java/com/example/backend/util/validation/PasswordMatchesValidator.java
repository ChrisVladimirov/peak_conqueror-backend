package com.example.backend.util.validation;

import com.example.backend.models.DTOs.UserRegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        UserRegisterDTO registerDTO = (UserRegisterDTO) obj;
        return registerDTO.getPassword().equals(registerDTO.getConfirmPassword());
    }
}
