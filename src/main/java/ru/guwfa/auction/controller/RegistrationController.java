package ru.guwfa.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.guwfa.auction.controller.util.ControllerUtils;
import ru.guwfa.auction.domain.dataTransferObject.UserDataTransferObject;
import ru.guwfa.auction.domain.dataTransferObject.response.CaptchaResponseDataTransferObject;
import ru.guwfa.auction.service.UserService;
import ru.guwfa.auction.validator.UserValidator;

import javax.validation.Valid;
import java.util.*;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService UserService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    @Autowired
    private ResourceBundle resourceBundle;

    @Autowired
    private UserValidator UserValidator;

    @InitBinder("UserDataTransferObject")
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(UserValidator);
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("g-recaptcha-response") String captchaResponse,
                          Model model,
                          @Valid UserDataTransferObject UserDataTransferObject,
                          BindingResult bindingResult){
        String url = String.format(CAPTCHA_URL, recaptchaSecret, captchaResponse);
        CaptchaResponseDataTransferObject response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDataTransferObject.class);

        if (!Objects.requireNonNull(response).isSuccess()) {
            model.addAttribute("captchaError", resourceBundle.getString("error.captcha"));
        }

        if (bindingResult.hasErrors() || !Objects.requireNonNull(response).isSuccess()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("User", UserDataTransferObject);

            return "registration";
        } else {
            UserService.addUser(UserDataTransferObject);
            model.addAttribute("message", resourceBundle.getString("message.followLinkInEmail"));
            return "login";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model,
                           @PathVariable String code){
        boolean isActivated = UserService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", resourceBundle.getString("message.activationCode.valid"));
        } else {
            model.addAttribute("message", resourceBundle.getString("message.activationCode.invalid"));
        }
        return "login";
    }
}



