package com.example.demo.validation;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target( { ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthValidator.class)
public @interface BirthDate {
    String message() default "Birth date must be present and greater or equal than 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
