package com.crewmaker.dto;

import com.crewmaker.entity.Event;

import java.sql.Time;
import java.util.Date;


public class EventDTO {
    private int eventID;
    private String eventName;
    private Date eventDate;
    private String eventPlaceName;
    private Time eventDuration;
    private Time eventTime;

    public EventDTO(Event event){
        eventID = event.getEventId();
        eventName = event.getName();
        eventDate = event.getDate();
        eventPlaceName = event.getEventPlace().getName();
        eventDuration = event.getEventDuration();
        eventTime = event.getEventTime();

    }

    public Time getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Time eventDuration) {
        this.eventDuration = eventDuration;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
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
