package ru.guwfa.auction.domain.dataTransferObject.response;

public class ResponseToPriceInformation {
    private Long rate;
    private String date;
    private String Username;

    public ResponseToPriceInformation(Long rate, String date, String Username) {
        this.rate = rate;
        this.date = date;
        this.Username = Username;
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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

}
