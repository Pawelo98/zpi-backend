package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="photoLink")
    @JsonIgnore
    private EventPlaceImage photoLink;

    @Column(name="isAccepted")
    private Boolean isAccepted;

    @Column(name="isArchived")
    private Boolean isArchived;

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    @OneToMany(mappedBy="eventPlace", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlaceOpinion> eventPlaceEventPlaceOpinions;

    @OneToMany(mappedBy="id.eventPlace", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlaceSportsCategory> eventPlaceSportsCategories;


    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "EventPlaceSportsCategory",
            joinColumns = { @JoinColumn(name = "eventPlaceID") },
            inverseJoinColumns = { @JoinColumn(name = "sportsCategoryID") }
    )
    Set<SportsCategory> sportsCategory = new HashSet<>();


    public Set<SportsCategory> getSportsCategory() {
        return sportsCategory;
    }

    public void setSportsCategory(Set<SportsCategory> sportsCategory) {
        this.sportsCategory = sportsCategory;
    }

    @JsonIgnore
    @OneToMany(mappedBy="eventPlace", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Event> eventPlaceEvents;

    public EventPlace(User userRequesting, String name, String description, String city,
                      String postCode, String street, String streetNumber, EventPlaceImage photoLink) {
        this.userRequesting = userRequesting;
        this.name = name;
        this.description = description;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.photoLink = photoLink;
        this.isAccepted = false;
        this.isArchived = false;
    }
    public String getUserAcceptingUsername() {
        return userAccepting.getUsername();
    }

    public String getUserRequestingUsername() {
        return userRequesting.getUsername();
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

    public EventPlaceImage getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(EventPlaceImage photoLink) {
        this.photoLink = photoLink;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
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
