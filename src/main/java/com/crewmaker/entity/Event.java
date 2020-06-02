package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventID")
    private int eventId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="cyclePeriodID")
    private CyclePeriod cyclePeriod;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="eventStatusID")
    private EventStatus eventStatus;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="eventPlaceID")
    private EventPlace eventPlace;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="sportsCategoryID")
    private SportsCategory sportsCategory;

    @OneToMany(mappedBy="id.event", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Participation> eventParticipations;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userID")
    private User userInitiator;

    @Column(name="eventName")
    private String name;

    @Column(name="eventDescription")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="eventDate")
    private Date date;

    @Column(name="eventMaxPlayers")
    private int maxPlayers;

    @Column(name="isCyclic")
    private boolean isCyclic;

    @Column(name="eventTime")
    private Time eventTime;

    @Column(name="eventDuration")
    private Time eventDuration;

    public Event(CyclePeriod cyclePeriod, EventStatus eventStatus, EventPlace eventPlace, SportsCategory sportsCategory,
                 String name, String description, Date date, int maxPlayers, boolean isCyclic, Time eventTime,
                 Time eventDuration, User userInitiator) {
        this.cyclePeriod = cyclePeriod;
        this.eventStatus = eventStatus;
        this.eventPlace = eventPlace;
        this.sportsCategory = sportsCategory;
        this.name = name;
        this.description = description;
        this.date = date;
        this.maxPlayers = maxPlayers;
        this.isCyclic = isCyclic;
        this.eventTime = eventTime;
        this.eventDuration = eventDuration;
        this.userInitiator = userInitiator;
    }

    public Event() {}

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public CyclePeriod getCyclePeriod() {
        return cyclePeriod;
    }

    public void setCyclePeriod(CyclePeriod cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public EventPlace getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(EventPlace eventPlace) {
        this.eventPlace = eventPlace;
    }

    public SportsCategory getSportsCategory() {
        return sportsCategory;
    }

    public void setSportsCategory(SportsCategory sportsCategory) {
        this.sportsCategory = sportsCategory;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public boolean isCyclic() {
        return isCyclic;
    }

    public void setCyclic(boolean cyclic) {
        isCyclic = cyclic;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public Time getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Time eventDuration) {
        this.eventDuration = eventDuration;
    }

    public Set<Participation> getEventParticipations() {
        return eventParticipations;
    }

    public void setEventParticipations(Set<Participation> eventParticipations) {
        this.eventParticipations = eventParticipations;
    }

    public User getUserInitiator() {
        return userInitiator;
    }

    public void setUserInitiator(User userInitiator) {
        this.userInitiator = userInitiator;
    }

    public void addParticipator(User user, Event event, Integer queuePosition) {
        if (eventParticipations == null) {
            eventParticipations = new HashSet<>();
        }
        Participation participation = new Participation(user, event, queuePosition);
        eventParticipations.add(participation);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", cyclePeriod=" + cyclePeriod +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", maxPlayers=" + maxPlayers +
                ", isCyclic=" + isCyclic +
                ", eventTime=" + eventTime +
                ", eventDuration=" + eventDuration +
                '}';
    }
}
