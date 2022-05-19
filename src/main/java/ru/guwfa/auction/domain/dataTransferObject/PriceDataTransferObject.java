package ru.guwfa.auction.domain.dataTransferObject;

import java.util.Objects;

public class PriceDataTransferObject {
    private Long LotInAuId;
    private Long rate;
    private String date;
    private Long UserId;

    public PriceDataTransferObject() {
    }

    public Long getLotInAuId() {
        return LotInAuId;
    }

    public void setLotInAuId(Long LotInAuId) {
        this.LotInAuId = LotInAuId;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDataTransferObject that = (PriceDataTransferObject) o;
        return LotInAuId.equals(that.LotInAuId) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(LotInAuId, date);
    }

}
