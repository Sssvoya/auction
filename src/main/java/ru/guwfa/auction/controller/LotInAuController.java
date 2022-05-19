package ru.guwfa.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.guwfa.auction.service.LotInAUservice;
import ru.guwfa.auction.service.SubscriptionService;
import ru.guwfa.auction.validator.LotInAuValidator;
import ru.guwfa.auction.validator.MessageDataTransferObjectValidator;

@Controller
@RequestMapping("/LotInAu")
public class LotInAuController {
    private final static int PAGE_SIZE = 6;

    @Autowired
    private LotInAUservice LotInAUservice;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private LotInAuValidator LotInAuValidator;

    @Autowired
    private MessageDataTransferObjectValidator messageDataTransferObjectValidator;

    @InitBinder("LotInAuDataTransferObject")
    protected void initLotInAuBinder(WebDataBinder binder) {
        binder.setValidator(LotInAuValidator);
    }

    @InitBinder("messageDataTransferObject")
    protected void initMessageBinder(WebDataBinder binder) {
        binder.setValidator(messageDataTransferObjectValidator);
    }

}
