package ru.guwfa.auction.dataAccessObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class SubscriptionDataAccessObject {

    @Autowired
    private EntityManager em;

    @Transactional
    public void removeAllSubscribers(Long LotInAuId) {

        String query = "DELETE FROM subscription " +
                "WHERE subscription.LotInAu_id = :LotInAuId";

        em.createNativeQuery(query)
                .setParameter("LotInAuId", LotInAuId)
                .executeUpdate();
    }
}
