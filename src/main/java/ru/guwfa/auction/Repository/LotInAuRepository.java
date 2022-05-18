package ru.guwfa.auction.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.guwfa.auction.domain.LotInAu;

@Repository
public interface LotInAuRepository extends JpaRepository<LotInAu, Long> {
    Page<LotInAu> findAllByStatusOrderBytimeStartAsc(String status, Pageable pageable);
}
