package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ResourceBundle;

@Component
public class LotInAuValidator implements Validator {

    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MAXIMUM_NAME_LENGTH = 64;

    private static final int MINIMUM_DESCRIPTION_LENGTH = 1;
    private static final int MAXIMUM_DESCRIPTION_LENGTH = 1024;

    private static final long MINIMUM_INITIAL_rate = 1L;
    private static final long MAXIMUM_INITIAL_rate = 90000000L; // 90 000 000

    private static final int MINIMUM_TIME_STEP = 1; // minutes
    private static final int MAXIMUM_TIME_STEP = 60; // minutes

    private ResourceBundle resourceBundle;

    public LotInAuValidator() {
    }

    @Autowired
    public LotInAuValidator(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
