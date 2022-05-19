package ru.guwfa.auction.dataAccessObject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.guwfa.auction.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PriceDataAccessObject {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public User findWinner(Long LotInAuId) {

        String query = "SELECT User.* " +
                "FROM User JOIN Price " +
                "ON User.id = (" +
                "SELECT Price.User_id FROM Price " +
                "WHERE Price.LotInAu_id = :LotInAuId " +
                "ORDER BY Price.date DESC LIMIT 1" +
                ") " +
                "WHERE User.balance >= Price.rate " +
                "ORDER BY Price.rate DESC " +
                "LIMIT 1";

        return (User)em.createNativeQuery(query, User.class)
                .setParameter("LotInAuId", LotInAuId)
                .getResultStream().findFirst().orElse(null);
    }
}
