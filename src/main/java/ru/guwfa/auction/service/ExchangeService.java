package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.domain.User;

@Service
public class ExchangeService {
    @Autowired
    private UserService UserService;

    public void sendMoney(User userFrom, User userTo, Long value){


    }

}
