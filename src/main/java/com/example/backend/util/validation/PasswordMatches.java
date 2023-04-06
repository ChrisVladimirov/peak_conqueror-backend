package com.example.backend.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface PasswordMatches {
    String message() default "Passwords don't match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
