package ru.guwfa.auction.dataAccessObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.User;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<User> findAllSubscribers(Long LotInAuId) {
        String query = "SELECT User.* FROM User " +
                "WHERE User.id = " +
                "(SELECT subscription.subscriber_id FROM subscription " +
                "WHERE subscription.LotInAu_id = :LotInAuId) " +
                "ORDER BY User.id";

        return em.createNativeQuery(query, User.class)
                .setParameter("LotInAuId", LotInAuId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Page<LotInAu> findAllLotInAus(Long UserId, Pageable pageable) {

        String queryLotInAuPage = "SELECT LotInAu.* FROM LotInAu " +
                "WHERE LotInAu.id IN " +
                "(SELECT subscription.LotInAu_id FROM subscription " +
                "WHERE subscription.subscriber_id = :UserId)" +
                "ORDER BY LotInAu.id " +
                "LIMIT :startIndex, :size";

        List<LotInAu> LotInAus = em.createNativeQuery(queryLotInAuPage, LotInAu.class)
                .setParameter("UserId", UserId)
                .setParameter("startIndex", pageable.getPageNumber() * pageable.getPageSize())
                .setParameter("size", pageable.getPageSize())
                .getResultList();

        String queryLotInAuCount = "SELECT COUNT(*) FROM LotInAu " +
                "WHERE LotInAu.id IN " +
                "(SELECT subscription.LotInAu_id FROM subscription " +
                "WHERE subscription.subscriber_id = :UserId)";

        BigInteger LotInAuCount = (BigInteger)em.createNativeQuery(queryLotInAuCount)
                .setParameter("UserId", UserId)
                .getSingleResult();



        Page<LotInAu> pageLotInAu = new PageImpl<LotInAu>(LotInAus, pageable, LotInAuCount.longValue());

        return pageLotInAu;
    }

    @Transactional(readOnly = false)
    public void saveSubscription(Long LotInAuId, Long UserId) {
        String query = "INSERT INTO auction.subscription (LotInAu_id, subscriber_id) " +
                "VALUES (:LotInAuId, :subscriberId)";

        em.createNativeQuery(query)
                .setParameter("LotInAuId", LotInAuId)
                .setParameter("subscriberId", UserId)
                .executeUpdate();
    }

    @Transactional(readOnly = false)
    public void deleteSubscription(Long LotInAuId, Long UserId) {
        String query = "DELETE FROM auction.subscription " +
                "WHERE LotInAu_id = :LotInAuId AND subscriber_id = :subscriberId";

        em.createNativeQuery(query)
                .setParameter("LotInAuId", LotInAuId)
                .setParameter("subscriberId", UserId)
                .executeUpdate();
    }
}
