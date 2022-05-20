package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.guwfa.auction.domain.dataTransferObject.UserDataTransferObject;
import ru.guwfa.auction.service.UserService;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
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

    @Autowired
    private ResourceBundle resourceBundle;

    @Autowired
    private UserService UserService;
    @Override
    public void validate(Object obj, Errors errors) {
        UserDataTransferObject UserDataTransferObject = (UserDataTransferObject) obj;
        if (UserDataTransferObject.getUsername() != null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Username", "Username.empty", resourceBundle.getString("error.empty"));
            validateUsername(UserDataTransferObject, errors);
        }

        if (UserDataTransferObject.getEmail() != null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", resourceBundle.getString("error.empty"));
            validateEmail(UserDataTransferObject, errors);
        }

        if (UserDataTransferObject.getPassword() != null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", resourceBundle.getString("error.empty"));
            validatePass(UserDataTransferObject, errors);
        }

        if (UserDataTransferObject.getNewEmail() != null && !UserDataTransferObject.getNewEmail().isBlank())
            validateNewEmail(UserDataTransferObject, errors);

        if (UserDataTransferObject.getNewPassword() != null && !UserDataTransferObject.getNewPassword().isBlank())
            validateNewPass(UserDataTransferObject, errors);
    }

    private void validateNewEmail(UserDataTransferObject UserDataTransferObject, Errors errors) {
        if (UserDataTransferObject.getNewEmail().length() > EMAIL_MAX_LENGTH) { // проверка на макс длину
            errors.rejectValue("newEmail", "email.length", resourceBundle.getString("error.length"));
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(UserDataTransferObject.getNewEmail());

        boolean isValid = matcher.find();

        if (!isValid) {
            errors.rejectValue("newEmail", "newEmail.invalid", resourceBundle.getString("error.email.invalid"));
        }

        if (UserService.isExistsByEmail(UserDataTransferObject.getNewEmail())) {
            errors.rejectValue("newEmail", "newEmail.exists", resourceBundle.getString("error.alreadyExists"));
        }
    }

    private void validateNewPass(UserDataTransferObject UserDataTransferObject, Errors errors) {
        if (UserDataTransferObject.getNewPassword().length() > PASSWORD_MAX_LENGTH ||
                UserDataTransferObject.getNewPassword().length() < PASSWORD_MIN_LENGTH) {
            errors.rejectValue("newPassword", "newPassword.length", resourceBundle.getString("error.length"));
        }
    }

    private void validatePass(UserDataTransferObject UserDataTransferObject, Errors errors) {
        if (UserDataTransferObject.getPassword().length() > PASSWORD_MAX_LENGTH ||
                UserDataTransferObject.getPassword().length() < PASSWORD_MIN_LENGTH) {
            errors.rejectValue("password", "password.length", resourceBundle.getString("error.length"));
        }
    }

    private void validateUsername(UserDataTransferObject UserDataTransferObject, Errors errors) {
        if (UserDataTransferObject.getUsername().length() > UserNAME_MAX_LENGTH ||
                UserDataTransferObject.getUsername().length() < UserNAME_MIN_LENGTH
        ) {
            errors.rejectValue("Username", "Username.length", resourceBundle.getString("error.length"));
        }

        if (UserService.isExistsByUsername(UserDataTransferObject.getUsername())) {
            errors.rejectValue("Username", "Username.exists", resourceBundle.getString("error.alreadyExists"));
        }
    }

    private void validateEmail(UserDataTransferObject UserDataTransferObject, Errors errors) {
        if (UserDataTransferObject.getEmail().length() > EMAIL_MAX_LENGTH) { // проверка на макс длину
            errors.rejectValue("email", "email.length", resourceBundle.getString("error.length"));
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(UserDataTransferObject.getEmail());

        boolean isValid = matcher.find();

        if (!isValid) {
            errors.rejectValue("email", "email.invalid", resourceBundle.getString("error.email.invalid"));
        }

        if (UserService.isExistsByEmail(UserDataTransferObject.getEmail())) {
            errors.rejectValue("email", "email.exists", resourceBundle.getString("error.alreadyExists"));
        }
    }

}
