package com.crewmaker.dto;

import com.crewmaker.entity.EventPlace;

public class EventPlaceDTO {
    private int eventPlaceId;
    private String name;
    private String city;
    private String postCode;
    private String street;
    private String streetNumber;

    public EventPlaceDTO(EventPlace eventPlace) {
        this.eventPlaceId = eventPlace.getEventPlaceId();
        this.name = eventPlace.getName();
        this.city = eventPlace.getCity();
        this.postCode = eventPlace.getPostCode();
        this.street = eventPlace.getStreet();
        this.streetNumber = eventPlace.getStreetNumber();
    }

    public int getEventPlaceId() {
        return eventPlaceId;
    }

    public void setEventPlaceId(int eventPlaceId) {
        this.eventPlaceId = eventPlaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
