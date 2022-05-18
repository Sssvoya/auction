package ru.guwfa.auction.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    private final static int UserNAME_MIN_LENGTH = 2;
    private final static int UserNAME_MAX_LENGTH = 16;
    private final static int PASSWORD_MIN_LENGTH = 4;
    private final static int PASSWORD_MAX_LENGTH = 12;
    private final static String EMAIL_PATTERN = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
    private final static int EMAIL_MAX_LENGTH = 32;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
