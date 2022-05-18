package ru.guwfa.auction.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MessageDataTransferObjectValidator implements Validator {

    private final static int TEXT_MIN_LENGTH = 1;
    private final static int TEXT_MAX_LENGTH = 255;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
