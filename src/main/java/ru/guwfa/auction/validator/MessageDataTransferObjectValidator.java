package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.guwfa.auction.domain.dataTransferObject.MessageDataTransferObject;

import java.util.ResourceBundle;

@Component
public class MessageDataTransferObjectValidator implements Validator {

    private final static int TEXT_MIN_LENGTH = 1;
    private final static int TEXT_MAX_LENGTH = 255;

    @Autowired
    private ResourceBundle resourceBundle;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text.empty", resourceBundle.getString("error.empty"));

        MessageDataTransferObject messageDataTransferObject = (MessageDataTransferObject)obj;

        validateText(messageDataTransferObject, errors);

    }

    private void validateText(MessageDataTransferObject messageDataTransferObject, Errors errors) {
        if(messageDataTransferObject.getText().length() > TEXT_MAX_LENGTH ||
                messageDataTransferObject.getText().length() < TEXT_MIN_LENGTH
        ){
            errors.rejectValue("text", "text.length", resourceBundle.getString("error.length"));
        }
    }
}
