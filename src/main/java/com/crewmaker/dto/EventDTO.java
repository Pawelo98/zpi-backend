package com.crewmaker.dto;

import com.crewmaker.entity.Event;

import java.util.Date;


public class EventDTO {
    private int eventID;
    private String eventName;
    private Date eventDate;
    private String eventPlaceName;

    public EventDTO(Event event){
        eventID = event.getEventId();
        eventName = event.getName();
        eventDate = event.getDate();
        eventPlaceName = event.getEventPlace().getName();
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
}
