package com.crewmaker.model.UserProfile;

import java.util.Date;

public class UserProfileEvent {
    private String sportsCategory;
    private Date date;
    private Date eventTime;
    private String eventPlaceName;
    private String eventPlaceCity;
    private String eventPlaceStreet;
    private String eventPlaceNumber;
    private long activeParticipants;
    private int maxParticipants;

    public UserProfileEvent(String sportsCategory, Date date, Date eventTime, String eventPlaceName, String eventPlaceCity,
                            String eventPlaceStreet, String eventPlaceNumber, long activeParticipants, int maxParticipants) {
        this.sportsCategory = sportsCategory;
        this.date = date;
        this.eventTime = eventTime;
        this.eventPlaceName = eventPlaceName;
        this.eventPlaceCity = eventPlaceCity;
        this.eventPlaceStreet = eventPlaceStreet;
        this.eventPlaceNumber = eventPlaceNumber;
        this.activeParticipants = activeParticipants;
        this.maxParticipants = maxParticipants;
    }

    public String getSportsCategory() {
        return sportsCategory;
    }

    public Date getDate() {
        return date;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public String getEventPlaceName() {
        return eventPlaceName;
    }

    public String getEventPlaceCity() {
        return eventPlaceCity;
    }

    public String getEventPlaceStreet() {
        return eventPlaceStreet;
    }

    public String getEventPlaceNumber() {
        return eventPlaceNumber;
    }

    public long getActiveParticipants() {
        return activeParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }
}
