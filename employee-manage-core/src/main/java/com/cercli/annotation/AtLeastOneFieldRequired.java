package com.cercli.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneFieldRequiredValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneFieldRequired {
    String message() default "At least one of the fields must be non-null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] fields(); // List of fields to check
}
