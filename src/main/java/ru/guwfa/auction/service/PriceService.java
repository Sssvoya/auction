package ru.guwfa.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.guwfa.auction.Repository.PriceRepository;
import ru.guwfa.auction.dataAccessObject.PriceDataAccessObject;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.Price;
import ru.guwfa.auction.domain.User;

import java.util.Date;

@Service
public class PriceService {
    @Autowired
    private PriceRepository PriceRepository;

    @Autowired
    private PriceDataAccessObject PriceDataAccessObject;


    public void addPrice(User User, LotInAu LotInAu) {
        Price Price = new Price();

        Price.setUser(User);
        Price.setDate(LotInAu.gettimeStart());
        Price.setLotInAu(LotInAu);
        Price.setRate(LotInAu.getinitialRate());

        PriceRepository.save(Price);
    }

    public void addPrice(User User, LotInAu LotInAu, Long rate, Date date) {
        Price Price = new Price();

        Price.setUser(User);
        Price.setDate(date);
        Price.setLotInAu(LotInAu);
        Price.setRate(rate);

        PriceRepository.save(Price);
    }

    public Price getLastByLotInAuId(Long id) {
        return PriceRepository.findFirstByLotInAuIdOrderByDateDesc(id);
    }

    public User getWinner(Long LotInAuId){
        return PriceDataAccessObject.findWinner(LotInAuId);
    }

    public Price getFirst(Long LotInAuId){
        return PriceRepository.findFirstByLotInAuIdOrderByDateDesc(LotInAuId);
    }

}
