package ru.guwfa.auction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.guwfa.auction.domain.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Price findFirstByLotInAuIdOrderByDateDesc(Long id);
}

