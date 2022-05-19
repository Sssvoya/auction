package ru.guwfa.auction.dataAccessObject;

import com.google.common.base.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.StatusLot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Component
public class LotInAuDataAccessObject {
    @PersistenceContext
    private EntityManager em;

    public Page<LotInAu> findByFilter(String filterName, String filterDescription, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LotInAu> criteria = cb.createQuery(LotInAu.class);

        Root<LotInAu> root = criteria.from(LotInAu.class);
        Predicate predicate = cb.conjunction();

        if (!Strings.isNullOrEmpty(filterName)) {
            predicate = cb.and(cb.equal(root.get("name"), filterName));
        }
        if (!Strings.isNullOrEmpty(filterDescription)) {
            predicate.getExpressions().add(cb.like(root.get("description"), "%" + filterDescription + "%"));
        }

        // выводить только лоты со статусом "ACTIVE"
        predicate.getExpressions().add(cb.equal(root.get("status"), StatusLot.ACTIVE.name()));

        criteria.orderBy(cb.asc(root.get("timeStart"))); // сортировка по дате, по возрастанию

        criteria.where(predicate);

        TypedQuery<LotInAu> query = em.createQuery(criteria);
        int totalRows = query.getResultList().size(); // ра

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<LotInAu>(query.getResultList(), pageable, totalRows);
    }

    @Transactional(readOnly = true)
    public List<BigInteger> getLotInAuIDataTransferObjectBeUpdated() {

        String query = "SELECT LotInAu.id FROM LotInAu " +
                "WHERE LotInAu.end_time <= :nowDate " +
                "AND LotInAu.status = :oldStatus";

        return em.createNativeQuery(query)
                .setParameter("nowDate", new Date())
                .setParameter("oldStatus", StatusLot.ACTIVE.name())
                .getResultList();
    }
}
