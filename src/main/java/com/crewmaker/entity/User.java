package com.crewmaker.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userID")
    private int userId;

    @Column(name="login")
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="archived")
    private boolean archived;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="isAdmin")
    private boolean isAdmin;

    @OneToMany(mappedBy="userAccepting", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlace> userAcceptingEventPlaces;

    @OneToMany(mappedBy="userRequesting", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlace> userRequestingEventPlaces;

    @OneToMany(mappedBy="userAuthor", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<UserOpinion> userAuthorUserOpinions;

    @OneToMany(mappedBy="userAbout", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<UserOpinion> userAboutUserOpinions;

    @OneToMany(mappedBy="userAuthor", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<EventPlaceOpinion> userAuthorEventPlaceOpinions;

    @OneToMany(mappedBy="userManaging", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Event> userManagingEvents;

    public User(String login, String email, String password, String name, String surname, boolean archived, String phoneNumber, boolean isAdmin) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.archived = archived;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<EventPlace> getUserAcceptingEventPlaces() {
        return userAcceptingEventPlaces;
    }

    public void setUserAcceptingEventPlaces(Set<EventPlace> userAcceptingEventPlaces) {
        this.userAcceptingEventPlaces = userAcceptingEventPlaces;
    }

    public Set<EventPlace> getUserRequestingEventPlaces() {
        return userRequestingEventPlaces;
    }

    public void setUserRequestingEventPlaces(Set<EventPlace> userRequestingEventPlaces) {
        this.userRequestingEventPlaces = userRequestingEventPlaces;
    }

    public Set<UserOpinion> getUserAuthorUserOpinion() {
        return userAuthorUserOpinions;
    }

    public void setUserAuthorUserOpinion(Set<UserOpinion> userAuthorUserOpinions) {
        this.userAuthorUserOpinions = userAuthorUserOpinions;
    }

    public Set<UserOpinion> getUserAboutUserOpinion() {
        return userAboutUserOpinions;
    }

    public void setUserAboutUserOpinion(Set<UserOpinion> userAboutUserOpinions) {
        this.userAboutUserOpinions = userAboutUserOpinions;
    }

    public Set<UserOpinion> getUserAuthorUserOpinions() {
        return userAuthorUserOpinions;
    }

    public void setUserAuthorUserOpinions(Set<UserOpinion> userAuthorUserOpinions) {
        this.userAuthorUserOpinions = userAuthorUserOpinions;
    }

    public Set<UserOpinion> getUserAboutUserOpinions() {
        return userAboutUserOpinions;
    }

    public void setUserAboutUserOpinions(Set<UserOpinion> userAboutUserOpinions) {
        this.userAboutUserOpinions = userAboutUserOpinions;
    }

    public Set<EventPlaceOpinion> getUserAuthorEventPlaceOpinions() {
        return userAuthorEventPlaceOpinions;
    }

    public void setUserAuthorEventPlaceOpinions(Set<EventPlaceOpinion> userAuthorEventPlaceOpinions) {
        this.userAuthorEventPlaceOpinions = userAuthorEventPlaceOpinions;
    }

    public Set<Event> getUserManagingEvents() {
        return userManagingEvents;
    }

    public void setUserManagingEvents(Set<Event> userManagingEvents) {
        this.userManagingEvents = userManagingEvents;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", archived=" + archived +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
