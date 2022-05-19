package ru.guwfa.auction.domain.dataTransferObject.response;

public class LotInAuStatusResponseDataTransferObject {
    private Long id;
    private String status;

    public LotInAuStatusResponseDataTransferObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
