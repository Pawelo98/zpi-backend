package com.crewmaker.model.UserProfile;

import com.crewmaker.entity.Role;
import com.crewmaker.entity.UserProfileImage;
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
    private String description;


    public UserProfileUser(String username, String email, String phoneNumber, String description, String name, String surname) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.name = name;
        this.surname = surname;
    }

    public UserProfileUser(String username, String email, String phoneNumber, String description) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

}
