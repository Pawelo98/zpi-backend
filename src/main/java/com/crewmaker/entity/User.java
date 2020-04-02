package com.crewmaker.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserID")
    private int userId;

    @Column(name="Login")
    private String username;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;
    
    @Column(name="Name")
    private String name;

    @Column(name="Surname")
    private String surname;

    @Column(name="Archived")
    private boolean archived;

    @Column(name="PhoneNumber")
    private String phoneNumber;

    @Column(name="IsAdmin")
    private boolean isAdmin;

    @Column(name="PhotoLink")
    private String photoLink;

    @Column(name="Description")
    private String description;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        if (this.isEnabled()) {
            if (this.isAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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

    @OneToMany(mappedBy="id.user", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Participation> userParticipations;

    public User(String username, String email, String password, String name, String surname, boolean archived, String phoneNumber,
                boolean isAdmin, String photoLink, String description) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.archived = archived;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.photoLink = photoLink;
        this.description = description;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Participation> getUserParticipations() {
        return userParticipations;
    }

    public void setUserParticipations(Set<Participation> userParticipations) {
        this.userParticipations = userParticipations;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
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
