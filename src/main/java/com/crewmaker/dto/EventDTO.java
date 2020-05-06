package com.crewmaker.dto;

import com.crewmaker.entity.Event;

import java.sql.Time;
import java.util.Date;


public class EventDTO {
    private int eventID;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventPlaceName;
    private String eventPlaceStreetName;
    private String eventPlaceCity;
    private int maxPlayers;
    private String eventSportName;
    private String eventPlaceStreetNumber;

    public EventDTO(Event event){
        eventID = event.getEventId();
        eventName = event.getName();
        eventDate = event.getDate();
        eventTime = event.getEventTime();
        eventPlaceName = event.getEventPlace().getName();
        eventPlaceStreetName = event.getEventPlace().getStreet();
        eventPlaceCity = event.getEventPlace().getCity();
        maxPlayers = event.getMaxPlayers();
        eventSportName = event.getSportsCategory().getSportCategoryName();
        eventPlaceStreetNumber = event.getEventPlace().getStreetNumber();
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventPlaceName() {
        return eventPlaceName;
    }

    public void setEventPlaceName(String eventPlaceName) {
        this.eventPlaceName = eventPlaceName;
    }

    public String getEventPlaceStreetName() {
        return eventPlaceStreetName;
    }

    public void setEventPlaceStreetName(String eventPlaceStreetName) {
        this.eventPlaceStreetName = eventPlaceStreetName;
    }

    public String getEventPlaceCity() {
        return eventPlaceCity;
    }

    public void setEventPlaceCity(String eventPlaceCity) {
        this.eventPlaceCity = eventPlaceCity;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getEventSportName() {
        return eventSportName;
    }

    public void setEventSportName(String eventSportName) {
        this.eventSportName = eventSportName;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventPlaceStreetNumber() {
        return eventPlaceStreetNumber;
    }

    public void setEventPlaceStreetNumber(String eventPlaceStreetNumber) {
        this.eventPlaceStreetNumber = eventPlaceStreetNumber;
    }
}
