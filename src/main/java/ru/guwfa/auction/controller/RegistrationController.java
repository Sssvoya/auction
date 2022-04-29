package ru.guwfa.auction.controller;

import org.springframework.stereotype.Controller;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
}
