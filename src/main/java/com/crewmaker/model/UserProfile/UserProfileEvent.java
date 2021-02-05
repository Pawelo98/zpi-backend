package com.crewmaker.model.UserProfile;

import java.util.Date;

public class UserProfileEvent {
    private Date date;
    private Date eventTime;
    private int maxParticipants;

    public UserProfileEvent(Date date, Date eventTime, int maxParticipants) {
        this.date = date;
        this.eventTime = eventTime;
        this.maxParticipants = maxParticipants;
    }

    public Date getDate() {
        return date;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }
}
