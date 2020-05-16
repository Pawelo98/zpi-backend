package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="SportsCategory")
public class SportsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sportsCategoryID")
    private int sportsCategoryId;

    @Column(name="sportCategoryName")
    private String sportCategoryName;

    @Column(name="defaultPlayersNumber")
    private int defaultPlayersNumber;

    @JsonIgnore
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="eventplacesportscategory",
            joinColumns=@JoinColumn(name="sportsCategoryID"),
            inverseJoinColumns=@JoinColumn(name="eventPlaceID"))
    private List<EventPlace> eventPlaces;

    @JsonIgnore
    @OneToMany(mappedBy="sportsCategory", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Event> sportsCategoryEvents;

    @JsonIgnore
    @OneToMany(mappedBy="id.sportsCategory", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlaceSportsCategory> sportsCategoryEventPlaces;

    public SportsCategory(String sportCategoryName, int defaultPlayersNumber) {
        this.sportCategoryName = sportCategoryName;
        this.defaultPlayersNumber = defaultPlayersNumber;
    }

    public SportsCategory() {}

    public int getSportsCategoryId() {
        return sportsCategoryId;
    }

    public void setSportsCategoryId(int sportsCategoryId) {
        this.sportsCategoryId = sportsCategoryId;
    }

    public String getSportCategoryName() {
        return sportCategoryName;
    }

    public void setSportCategoryName(String sportCategoryName) {
        this.sportCategoryName = sportCategoryName;
    }

    public int getDefaultPlayersNumber() {
        return defaultPlayersNumber;
    }

    public void setDefaultPlayersNumber(int defaultPlayersNumber) {
        this.defaultPlayersNumber = defaultPlayersNumber;
    }

    public List<EventPlace> getEventPlaces() {
        return eventPlaces;
    }

    public void setEventPlaces(List<EventPlace> eventPlaces) {
        this.eventPlaces = eventPlaces;
    }

    public Set<Event> getSportsCategoryEvents() {
        return sportsCategoryEvents;
    }

    public void setSportsCategoryEvents(Set<Event> sportsCategoryEvents) {
        this.sportsCategoryEvents = sportsCategoryEvents;
    }

    public Set<EventPlaceSportsCategory> getSportsCategoryEventPlaces() {
        return sportsCategoryEventPlaces;
    }

    public void setSportsCategoryEventPlaces(Set<EventPlaceSportsCategory> sportsCategoryEventPlaces) {
        this.sportsCategoryEventPlaces = sportsCategoryEventPlaces;
    }

    @Override
    public String toString() {
        return "SportsCategory{" +
                "sportsCategoryId=" + sportsCategoryId +
                ", sportCategoryName='" + sportCategoryName + '\'' +
                ", defaultPlayersNumber=" + defaultPlayersNumber +
                '}';
    }
}
