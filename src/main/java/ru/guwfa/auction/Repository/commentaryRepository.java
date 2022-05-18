package ru.guwfa.auction.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.guwfa.auction.domain.commentary;

@Repository
public interface commentaryRepository extends JpaRepository<commentary, Long> {
    Page<commentary> findByLotInAuIdOrderByDateDesc(Long id, Pageable pageable);
}