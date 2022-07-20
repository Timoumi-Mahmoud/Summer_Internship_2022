package com.openclassrom.watchlist;

import com.openclassrom.watchlist.Priority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<Priority, String>{

    @Override
    public void initialize(Priority constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext validatorContext) {

        return value.trim().length()==1 && "LHM".contains(value.trim());
    }
}