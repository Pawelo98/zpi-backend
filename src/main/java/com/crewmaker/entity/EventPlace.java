package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="EventPlace")
public class EventPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventPlaceID")
    private int eventPlaceId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userAcceptingID")
    private User userAccepting;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userRequestingID")
    private User userRequesting;

    @Column(name="eventPlaceName")
    private String name;

    @Column(name="eventPlaceDescription")
    private String description;

    @Column(name="eventPlaceCity")
    private String city;

    @Column(name="eventPlacePostCode")
    private String postCode;

    @Column(name="eventPlaceStreet")
    private String street;

    @Column(name="eventPlaceStreetNumber")
    private String streetNumber;

    @Column(name="photoLink")
    private String photoLink;

    @OneToMany(mappedBy="eventPlace", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlaceOpinion> eventPlaceEventPlaceOpinions;

    @OneToMany(mappedBy="id.eventPlace", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlaceSportsCategory> eventPlaceSportsCategories;

    @JsonIgnore
    @OneToMany(mappedBy="eventPlace", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Event> eventPlaceEvents;

    public EventPlace(User userAccepting, User userRequesting, String name, String description, String city,
                      String postCode, String street, String streetNumber, String photoLink) {
        this.userAccepting = userAccepting;
        this.userRequesting = userRequesting;
        this.name = name;
        this.description = description;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.photoLink = photoLink;
    }

    public EventPlace() {}

    public int getEventPlaceId() {
        return eventPlaceId;
    }

    public void setEventPlaceId(int eventPlaceId) {
        this.eventPlaceId = eventPlaceId;
    }

    public User getUserAccepting() {
        return userAccepting;
    }

    public void setUserAccepting(User userAccepting) {
        this.userAccepting = userAccepting;
    }

    public User getUserRequesting() {
        return userRequesting;
    }

    public void setUserRequesting(User userRequesting) {
        this.userRequesting = userRequesting;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Set<EventPlaceOpinion> getEventPlaceEventPlaceOpinions() {
        return eventPlaceEventPlaceOpinions;
    }

    public void setEventPlaceEventPlaceOpinions(Set<EventPlaceOpinion> eventPlaceEventPlaceOpinions) {
        this.eventPlaceEventPlaceOpinions = eventPlaceEventPlaceOpinions;
    }

    public Set<EventPlaceSportsCategory> getEventPlaceSportsCategories() {
        return eventPlaceSportsCategories;
    }

    public void setEventPlaceSportsCategories(Set<EventPlaceSportsCategory> eventPlaceSportsCategories) {
        this.eventPlaceSportsCategories = eventPlaceSportsCategories;
    }

    public Set<Event> getEventPlaceEvents() {
        return eventPlaceEvents;
    }

    public void setEventPlaceEvents(Set<Event> eventPlaceEvents) {
        this.eventPlaceEvents = eventPlaceEvents;
    }

    @Override
    public String toString() {
        return "EventPlace{" +
                "eventPlaceId=" + eventPlaceId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                '}';
    }
}
