package ru.guwfa.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.guwfa.auction.service.LotInAUservice;
import ru.guwfa.auction.service.PriceService;
import ru.guwfa.auction.service.TimeService;
import ru.guwfa.auction.validator.PriceValidator;

@Controller
@RequestMapping("/LotInAu/{LotInAu}/rate")
@PreAuthorize("hasAuthority('User')")
public class PriceController {
    @Autowired
    private PriceService PriceService;

    @Autowired
    private LotInAUservice LotInAUservice;

    @Autowired
    private TimeService timeService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private PriceValidator PriceValidator;

    @InitBinder("PriceDataTransferObject")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(PriceValidator);
    }


}
