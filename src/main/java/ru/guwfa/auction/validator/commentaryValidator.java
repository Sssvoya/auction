package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "text.empty", resourceBundle.getString("error.empty"));

        commentary commentary = (commentary) obj;

        validateText(commentary, errors);
    }

    private void validateText(commentary commentary, Errors errors) {
        if (commentary.getText().length() > TEXT_MAX_LENGTH ||
                commentary.getText().length() < TEXT_MIN_LENGTH
        ) {
            errors.rejectValue("text", "text.length", resourceBundle.getString("error.length"));
        }
    }
}
