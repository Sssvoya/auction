package ru.guwfa.auction.domain.dataTransferObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LotInAu_id")
    private LotInAu LotInAu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_id")
    private User User;

    private Date date;
    private Long rate;

    public Price() {
    }

    public Price(LotInAu LotInAu, User User, Date dateTime, Long rate) {
        this.LotInAu = LotInAu;
        this.User = User;
        this.date = dateTime;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LotInAu getLotInAu() {
        return LotInAu;
    }
    public void setLotInAu(LotInAu LotInAu) {
        this.LotInAu = LotInAu;
    }

    public User getUser() {
        return User;
    }
    public void setUser(User User) {
        this.User = User;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date dateTime) {
        this.date = dateTime;
    }

    public Long getRate() {
        return rate;
    }
    public void setRate(Long rate) {
        this.rate = rate;
    }

}
