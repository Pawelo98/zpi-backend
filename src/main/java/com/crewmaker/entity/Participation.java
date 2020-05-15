package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Participation")
public class Participation {

    @EmbeddedId
    ParticipationId id;

    @Column(name="queuePosition")
    private Integer queuePosition;

    public Participation() {}

    public Participation(User user, Event event, Integer queuePosition) {
        this.id = new ParticipationId(user, event);
        this.queuePosition = queuePosition;
    }

    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }

    public Integer getQueueNumber() {
        return queuePosition;
    }

    public void setQueueNumber(Integer queueNumber) {
        this.queuePosition = queueNumber;
    }
}

@Embeddable
class ParticipationId  implements Serializable {

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
