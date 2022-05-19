package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.Repository.commentaryRepository;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.User;
import ru.guwfa.auction.domain.commentary;

import java.util.Date;

@Service
public class commentaryService {
    @Autowired
    private commentaryRepository commentaryRepository;
    public void addCommentary(commentary commentary, LotInAu LotInAu, User author){
        commentary.setAuthor(author);
        commentary.setLotInAu(LotInAu);
        commentary.setDate(new Date());

        commentaryRepository.save(commentary);
    }

    public void removeCommentary(commentary commentary){
        commentaryRepository.delete(commentary);
    }

    public Page<commentary> getAllByLotInAuId(Long id, Pageable pageable) {
        return commentaryRepository.findByLotInAuIdOrderByDateDesc(id, pageable);
    }
}
