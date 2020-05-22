package com.crewmaker.dto;

import com.crewmaker.entity.Event;

import java.sql.Time;
import java.util.Date;


public class EventDTO {
    private int eventID;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventDescription;
    private boolean isCyclic;
    private String cycleType;
    private int cycleLength;
    private int maxPlayers;
    private String eventPlaceName;
    private String eventPlaceDescription;
    private String eventPlaceStreetName;
    private String eventPlaceStreetNumber;
    private String eventPlacePostCode;
    private String eventPlaceCity;
    private String eventSportName;
    private Time eventDuration;
    private int eventPlaceID;

    public EventDTO(Event event){
        eventID = event.getEventId();
        eventName = event.getName();
        eventDate = event.getDate();
        eventTime = event.getEventTime();
        eventDescription = event.getDescription();
        isCyclic = event.isCyclic();
        if(isCyclic) {
            cycleType = event.getCyclePeriod().getCycleType();
            cycleLength = event.getCyclePeriod().getCycleLength();
        }
        eventPlaceDescription = event.getEventPlace().getDescription();
        eventPlacePostCode = event.getEventPlace().getPostCode();
        eventTime.setTime(event.getEventTime().getTime() - 3600000);
        this.eventPlaceID = event.getEventPlace().getEventPlaceId();
        eventPlaceName = event.getEventPlace().getName();
        eventPlaceStreetName = event.getEventPlace().getStreet();
        eventPlaceCity = event.getEventPlace().getCity();
        maxPlayers = event.getMaxPlayers();
        eventSportName = event.getSportsCategory().getSportCategoryName();
        eventPlaceStreetNumber = event.getEventPlace().getStreetNumber();
        eventDuration = event.getEventDuration();
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public boolean isCyclic() {
        return isCyclic;
    }

    public void setCyclic(boolean cyclic) {
        isCyclic = cyclic;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public int getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(int cycleLength) {
        this.cycleLength = cycleLength;
    }

    public String getEventPlaceDescription() {
        return eventPlaceDescription;
    }

    public void setEventPlaceDescription(String eventPlaceDescription) {
        this.eventPlaceDescription = eventPlaceDescription;
    }

    public String getEventPlacePostCode() {
        return eventPlacePostCode;
    }

    public void setEventPlacePostCode(String eventPlacePostCode) {
        this.eventPlacePostCode = eventPlacePostCode;
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

    public Time getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Time eventDuration) {
        this.eventDuration = eventDuration;
    }

    public int getEventPlaceID() {
        return eventPlaceID;
    }

    public void setEventPlaceID(int eventPlaceID) {
        this.eventPlaceID = eventPlaceID;
    }
}
