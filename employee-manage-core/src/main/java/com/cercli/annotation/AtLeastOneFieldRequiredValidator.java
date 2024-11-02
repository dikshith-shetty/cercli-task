package com.cercli.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;


@Slf4j
public class AtLeastOneFieldRequiredValidator implements ConstraintValidator<AtLeastOneFieldRequired, Object> {

    private String[] fields;

    @Override
    public void initialize(AtLeastOneFieldRequired constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return false;
        for (String fieldName : fields) {
            try {
                Field field = value.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(value);
                if (fieldValue != null) {
                    return true;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }
}
