package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.guwfa.auction.domain.commentary;

import java.util.ResourceBundle;

@Component
public class commentaryValidator implements Validator {

    private final static int TEXT_MIN_LENGTH = 0;
    private final static int TEXT_MAX_LENGTH = 64;

    @Autowired
    private ResourceBundle resourceBundle;

    @Override
    public boolean supports(Class<?> clazz) {
        return commentary.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
