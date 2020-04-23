package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="EventStatus")
public class EventStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventStatusID")
    private int eventStatusId;

    @Column(name="statusName")
    private String statusName;

    @JsonIgnore
    @OneToMany(mappedBy="eventStatus", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Event> eventStatusEvents;

    public EventStatus(String statusName) {
        this.statusName = statusName;
    }

    public EventStatus() {}

    public int getEventStatusId() {
        return eventStatusId;
    }

    public void setEventStatusId(int eventStatusId) {
        this.eventStatusId = eventStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<Event> getEventStatusEvents() {
        return eventStatusEvents;
    }

    public void setEventStatusEvents(Set<Event> eventStatusEvents) {
        this.eventStatusEvents = eventStatusEvents;
    }

    @Override
    public String toString() {
        return "EventStatus{" +
                "eventStatusId=" + eventStatusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
