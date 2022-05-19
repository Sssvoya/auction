package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.dataAccessObject.SubscriptionDataAccessObject;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.User;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionDataAccessObject subscriptionDataAccessObject;


    public void removeAllSubscribers(Long LotInAuId) {
        subscriptionDataAccessObject.removeAllSubscribers(LotInAuId);
    }

    public List<User> getAllSubscribersFor(Long LotInAuId) {
        return subscriptionDataAccessObject.findAllSubscribers(LotInAuId);
    }

    public Page<LotInAu> getAllLotInAusFor(Long UserId, Pageable pageable){
        return subscriptionDataAccessObject.findAllLotInAus(UserId, pageable);
    }

    public void addSubscription(Long LotInAuId, Long UserId){
        subscriptionDataAccessObject.saveSubscription(LotInAuId, UserId);
    }

    public void removeSubscription(Long LotInAuId, Long UserId){
        subscriptionDataAccessObject.deleteSubscription(LotInAuId, UserId);
    }
}
