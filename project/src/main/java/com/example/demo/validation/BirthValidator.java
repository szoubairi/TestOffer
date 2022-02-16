package com.example.demo.validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthValidator implements ConstraintValidator<BirthDate, LocalDate>{

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return ChronoUnit.YEARS.between(value, LocalDateTime.now()) >= 18;
    }

}
