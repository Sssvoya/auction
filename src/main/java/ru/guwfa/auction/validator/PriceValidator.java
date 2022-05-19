package ru.guwfa.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.User;
import ru.guwfa.auction.domain.dataTransferObject.PriceDataTransferObject;
import ru.guwfa.auction.service.LotInAUservice;
import ru.guwfa.auction.service.UserService;

import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class PriceValidator implements Validator {
    private static final long MAXIMUM_rate_VALUE = 99999999L; // 99 999 999

    @Autowired
    private LotInAUservice LotInAUservice;

    @Autowired
    private UserService UserService;

    @Autowired
    private ResourceBundle resourceBundle;

    @Override
    public boolean supports(Class<?> clazz) {
        return PriceDataTransferObject.class.equals(clazz);
    }


    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rate", "rate.empty", resourceBundle.getString("error.empty"));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "date.empty", resourceBundle.getString("error.empty"));

        PriceDataTransferObject priceDataTransferObject = (PriceDataTransferObject) obj;

        if(priceDataTransferObject.getRate() != null) {
            validateDate(priceDataTransferObject, errors); // date >= LotInAu.timeStart
            validateRate(priceDataTransferObject, errors); // rate >= LotInAu.finalrate
            validateUserBalance(priceDataTransferObject, errors); // rate <= User.balance
        }
    }

    private void validateUserBalance(PriceDataTransferObject priceDataTransferObject, Errors errors) {
        if (priceDataTransferObject.getUserId() != null) {
            Optional<User> optionalUser = UserService.getById(priceDataTransferObject.getUserId());

            if (optionalUser.isPresent()) {
                User User = optionalUser.get();

                if (User.getBalance() < priceDataTransferObject.getRate())
                    errors.rejectValue("rate", "rate.value.notEnough", resourceBundle.getString("error.rate.notEnough"));

            }
        }
    }

    private void validateDate(PriceDataTransferObject priceDataTransferObject, Errors errors) {
        if (priceDataTransferObject.getDate() != null) {
            Optional<LotInAu> optionalLotInAu = LotInAUservice.getById(priceDataTransferObject.getLotInAuId());

            if (optionalLotInAu.isPresent()) { // если лот с таким id есть в бд
                LotInAu LotInAu = optionalLotInAu.get();
                if (new Date().before(LotInAu.gettimeStart())) { // если время старта торгов еще не наступило
                    errors.rejectValue("rate", "rate.date.future", resourceBundle.getString("error.date.future"));
                }
            }
        }
    }

    private void validateRate(PriceDataTransferObject priceDataTransferObject, Errors errors) {
        if (priceDataTransferObject.getRate() != null) {
            Optional<LotInAu> optionalLotInAu = LotInAUservice.getById(priceDataTransferObject.getLotInAuId());

            if (optionalLotInAu.isPresent()) {
                LotInAu LotInAu = optionalLotInAu.get();
                if (priceDataTransferObject.getRate() <= LotInAu.getFinalrate()) { // если ввели ставку <= уже существующей для этого лота
                    errors.rejectValue("rate", "rate.value.min", resourceBundle.getString("error.rate.greaterThanLast"));
                } else if (priceDataTransferObject.getRate() > MAXIMUM_rate_VALUE) {
                    errors.rejectValue("rate", "rate.value.max", resourceBundle.getString("error.value.max") + " " + MAXIMUM_rate_VALUE);
                }
            }
        }
    }
}
