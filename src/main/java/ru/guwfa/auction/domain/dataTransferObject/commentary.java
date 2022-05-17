package ru.guwfa.auction.domain.dataTransferObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commentary")
public class commentary{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LotInAu_id")
    private LotInAu LotInAu;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }


    public LotInAu getLotInAu() {
        return LotInAu;
    }
    public void setLotInAu(LotInAu LotInAu) {
        this.LotInAu = LotInAu;
    }


    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }


    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}

