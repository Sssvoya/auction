package ru.guwfa.auction.domain;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "LotInAu")
public class LotInAu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_id")
    private User creator;

    private String name;
    private String description;
    private Date timeStart;
    private Date endTime;
    private Integer timeStep;
    private Long initialRate;
    private Long finalrate;
    private String filename;  // название файла (фотографии)
    private String status;

    @ManyToMany
    @JoinTable(
            name = "subscription",
            joinColumns = @JoinColumn(name = "LotInAu_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<User> subscribers = new HashSet<>();


    public LotInAu() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date gettimeStart() {
        return timeStart;
    }
    public void settimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getinitialRate() {
        return initialRate;
    }
    public void setinitialRate(Long initialRate) {
        this.initialRate = initialRate;
    }

    public Long getFinalrate() {
        return finalrate;
    }
    public void setFinalrate(Long finalrate) {
        this.finalrate = finalrate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreator() {
        return creator;
    }
    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getTimeStep() {
        return timeStep;
    }
    public void setTimeStep(Integer timeStep) {
        this.timeStep = timeStep;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }
    private void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public boolean isSubscriber(User User){
        return subscribers.contains(User);
    }
    public void removeAllSubscribers(){
        subscribers.clear();
    }

    @Override
    public String toString() {
        return "LotInAu{" +
                "id=" + id +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}