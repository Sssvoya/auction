package ru.guwfa.auction.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PriceValidator implements Validator {
    private static final long MAXIMUM_rate_VALUE = 99999999L; // 99 999 999

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
