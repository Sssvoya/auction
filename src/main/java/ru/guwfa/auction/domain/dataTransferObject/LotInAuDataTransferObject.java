package ru.guwfa.auction.domain.dataTransferObject;

import java.util.Objects;

public class LotInAuDataTransferObject {
    private String name;
    private String description;
    private Long initialRate;
    private String timeStart;
    private Integer timeStep;


    public LotInAuDataTransferObject() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(Integer timeStep) {
        this.timeStep = timeStep;
    }

    public Long getinitialRate() {
        return initialRate;
    }

    public void setinitialRate(Long initialRate) {
        this.initialRate = initialRate;
    }

    public String gettimeStart() {
        return timeStart;
    }

    public void settimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotInAuDataTransferObject LotInAuInAuDataTransferObject = (LotInAuDataTransferObject) o;
        return name.equals(LotInAuInAuDataTransferObject.name) &&
                Objects.equals(description, LotInAuInAuDataTransferObject.description) &&
                timeStart.equals(LotInAuInAuDataTransferObject.timeStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, timeStart);
    }
}
