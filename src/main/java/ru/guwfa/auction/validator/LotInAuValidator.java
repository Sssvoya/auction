package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.guwfa.auction.domain.dataTransferObject.LotInAuDataTransferObject;

import java.util.Date;
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
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", resourceBundle.getString("error.empty"));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty",  resourceBundle.getString("error.empty"));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "initialRate", "initialRate.empty",  resourceBundle.getString("error.empty"));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeStart", "timeStart.empty",  resourceBundle.getString("error.empty"));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeStep", "timeStep.empty",  resourceBundle.getString("error.empty"));

        LotInAuDataTransferObject LotInAuInAuDataTransferObject = (LotInAuDataTransferObject) obj;

        /* only future is available */
        validateTimeStart(LotInAuInAuDataTransferObject, errors);

        /* min name.length <= name.length <= max name.length */
        validateName(LotInAuInAuDataTransferObject, errors);

        /* min description.length <= description.length <= max description.length */
        validateDescription(LotInAuInAuDataTransferObject, errors);

        /* min initialRate.value <= initialRate.value <= max initialRate.value */
        validateInitialRate(LotInAuInAuDataTransferObject, errors);

        /* min timeStep.value <= timeStep.value <= max timeStep.value */
        validateTimeStep(LotInAuInAuDataTransferObject, errors);

    }

    private void validateTimeStep(LotInAuDataTransferObject LotInAuInAuDataTransferObject, Errors errors) {
        if (LotInAuInAuDataTransferObject.getTimeStep() != null) {
            if (LotInAuInAuDataTransferObject.getTimeStep() < MINIMUM_TIME_STEP) {
                errors.rejectValue("timeStep", "timeStep.value.min", resourceBundle.getString("error.value.min") + " " + MINIMUM_TIME_STEP);
            } else if (LotInAuInAuDataTransferObject.getTimeStep() > MAXIMUM_TIME_STEP) {
                errors.rejectValue("timeStep", "timeStep.value.max", resourceBundle.getString("error.value.max") + " " + MAXIMUM_TIME_STEP);
            }
        }
    }

    private void validateTimeStart(LotInAuDataTransferObject LotInAuInAuDataTransferObject, Errors errors) {
        if (LotInAuInAuDataTransferObject.gettimeStart() != null) {
            if (LotInAuInAuDataTransferObject.gettimeStart().equals("Invalid Date")) { // возникает, когда дата на view не выбрана
                errors.rejectValue("timeStart", "timeStart.empty.or.invalid", resourceBundle.getString("error.empty"));

            } else if (new Date().after(new Date(LotInAuInAuDataTransferObject.gettimeStart()))) { //допустимо только будущее время
                errors.rejectValue("timeStart", "timeStart.onlyFuture", resourceBundle.getString("error.timeStart.onlyFuture"));
            }
        }
    }

    private void validateInitialRate(LotInAuDataTransferObject LotInAuInAuDataTransferObject, Errors errors) {
        if (LotInAuInAuDataTransferObject.getinitialRate() != null) {
            if (LotInAuInAuDataTransferObject.getinitialRate() < MINIMUM_INITIAL_rate) {
                errors.rejectValue("initialRate", "initialRate.value.min", resourceBundle.getString("error.value.min") + " " + MINIMUM_INITIAL_rate);
            } else if (LotInAuInAuDataTransferObject.getinitialRate() > MAXIMUM_INITIAL_rate) {
                errors.rejectValue("initialRate", "initialRate.value.max", resourceBundle.getString("error.value.max") + " " + MAXIMUM_INITIAL_rate);
            }
        }
    }

    private void validateName(LotInAuDataTransferObject LotInAuInAuDataTransferObject, Errors errors) {
        if (LotInAuInAuDataTransferObject.getName() != null) {
            if (LotInAuInAuDataTransferObject.getName().length() > MAXIMUM_NAME_LENGTH) { // если превышает допустимую длину
                errors.rejectValue("name", "name.length.min", resourceBundle.getString("error.length.max"));
            } else if (LotInAuInAuDataTransferObject.getName().length() < MINIMUM_NAME_LENGTH) {
                errors.rejectValue("name", "name.length.max", resourceBundle.getString("error.length.min"));
            }
        }
    }

    private void validateDescription(LotInAuDataTransferObject LotInAuInAuDataTransferObject, Errors errors) {
        if (LotInAuInAuDataTransferObject.getDescription() != null) {
            if (LotInAuInAuDataTransferObject.getName().length() > MAXIMUM_DESCRIPTION_LENGTH) { // если превышает допустимую длину
                errors.rejectValue("description", "description.length.max", resourceBundle.getString("error.length.max"));
            } else if (LotInAuInAuDataTransferObject.getDescription().length() < MINIMUM_DESCRIPTION_LENGTH) {
                errors.rejectValue("description", "description.length.min", resourceBundle.getString("error.length.min"));
            }
        }
    }

}
