package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.dataAccessObject.SubscriptionDataAccessObject;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionDataAccessObject subscriptionDataAccessObject;


    public void removeAllSubscribers(Long LotInAuId) {
        subscriptionDataAccessObject.removeAllSubscribers(LotInAuId);
    }

}
