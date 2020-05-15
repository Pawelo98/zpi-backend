package com.crewmaker.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="EventPlaceSportsCategory")
public class EventPlaceSportsCategory {

    @EmbeddedId
    EventPlaceSportsCategoryId id;

    @Column(name="maxEventsSimultaneously")
    private int maxEventsSimultaneously;

    public EventPlaceSportsCategory() {}

    public EventPlaceSportsCategory(EventPlace eventPlace, SportsCategory sportsCategory, int maxEventsSimultaneously) {
        this.id = new EventPlaceSportsCategoryId(eventPlace, sportsCategory);
        this.maxEventsSimultaneously = maxEventsSimultaneously;
    }

    public EventPlaceSportsCategory(EventPlace eventPlace, SportsCategory sportsCategory) {
        this.id = new EventPlaceSportsCategoryId(eventPlace, sportsCategory);
    }

    public EventPlaceSportsCategoryId getId() {
        return id;
    }

    public void setId(EventPlaceSportsCategoryId id) {
        this.id = id;
    }
}

@Embeddable
class EventPlaceSportsCategoryId implements Serializable {

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="eventPlaceID")
    private EventPlace eventPlace;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="sportsCategoryID")
    private SportsCategory sportsCategory;

    public EventPlaceSportsCategoryId() {}

    public EventPlaceSportsCategoryId(EventPlace eventPlace, SportsCategory sportsCategory) {
        this.eventPlace = eventPlace;
        this.sportsCategory = sportsCategory;
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
}
