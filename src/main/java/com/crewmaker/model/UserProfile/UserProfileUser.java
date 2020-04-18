package com.crewmaker.model.UserProfile;

public class UserProfileUser {
    private String username;
    private String email;
    private String phoneNumber;
    private String photoLink;
    private String description;

    public UserProfileUser(String username, String email, String phoneNumber, String photoLink, String description) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photoLink = photoLink;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getDescription() {
        return description;
    }
}
