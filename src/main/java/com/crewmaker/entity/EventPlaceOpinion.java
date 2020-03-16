package com.crewmaker.entity;

import javax.persistence.*;

@Entity
@Table(name="eventplaceopinion")
public class EventPlaceOpinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventPlaceOpinionID")
    private int eventPlaceOpinionId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="eventPlaceID")
    private EventPlace eventPlace;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="userID")
    private User userAuthor;

    @Column(name="title")
    private String title;

    @Column(name="message")
    private String message;

    @Column(name="grade")
    private int grade;

    public EventPlaceOpinion(EventPlace eventPlace, User userAuthor, String title, String message, int grade) {
        this.eventPlace = eventPlace;
        this.userAuthor = userAuthor;
        this.title = title;
        this.message = message;
        this.grade = grade;
    }

    public EventPlaceOpinion() {}

    public int getEventPlaceOpinionId() {
        return eventPlaceOpinionId;
    }

    public void setEventPlaceOpinionId(int eventPlaceOpinionId) {
        this.eventPlaceOpinionId = eventPlaceOpinionId;
    }

    public EventPlace getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(EventPlace eventPlace) {
        this.eventPlace = eventPlace;
    }

    public User getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(User userAuthor) {
        this.userAuthor = userAuthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "EventPlaceOpinion{" +
                "eventPlaceOpinionId=" + eventPlaceOpinionId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", grade=" + grade +
                '}';
    }
}
