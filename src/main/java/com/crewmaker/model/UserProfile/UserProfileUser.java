package com.crewmaker.model.UserProfile;

import com.crewmaker.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class UserProfileUser {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String photoLink;
    private String description;

    public UserProfileUser(String username, String email, String phoneNumber, String photoLink, String description, String name, String surname) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photoLink = photoLink;
        this.description = description;
        this.name = name;
        this.surname = surname;
    }

    public UserProfileUser(String username, String email, String phoneNumber, String photoLink, String description) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photoLink = photoLink;
        this.description = description;
    }

}
