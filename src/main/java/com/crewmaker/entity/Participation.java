package com.crewmaker.entity;

import javax.persistence.*;

@Entity
@Table(name="participation")
public class Participation {

    @EmbeddedId
    ParticipationId id;

    @Column(name="queueNumber")
    private int queueNumber;

    public Participation() {}

    public Participation(User user, Event event, int queueNumber) {
        this.id = new ParticipationId(user, event);
        this.queueNumber = queueNumber;
    }

    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }
}

@Embeddable
class ParticipationId {

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userID")
    private User user;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="eventID")
    private Event event;

    public ParticipationId() {}

    public ParticipationId(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
