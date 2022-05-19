package ru.guwfa.auction.domain.dataTransferObject.response;

public class TimerResponseDataTransferObject {
    private String content;

    public TimerResponseDataTransferObject() {
    }

    public TimerResponseDataTransferObject(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
