package com.crewmaker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserID")
    private Long userId;

    @Column(name="Username")
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

    @Column(name="Enabled")
    private boolean enabled;

    @Column(name="PhoneNumber")
    private String phoneNumber;

    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="ImageID")
    @JsonIgnore
    private UserProfileImage userProfileImage;

    @Column(name="Description")
    private String description;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserRoles",
            joinColumns = @JoinColumn(name = "UserPermittedID"),
            inverseJoinColumns = @JoinColumn(name = "RoleAssignedID"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy="userAccepting", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<EventPlace> userAcceptingEventPlaces;

    @OneToMany(mappedBy="userRequesting", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<EventPlace> userRequestingEventPlaces;

    @OneToMany(mappedBy="userAuthor", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<UserOpinion> userAuthorUserOpinions;

    @OneToMany(mappedBy="userAbout", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<UserOpinion> userAboutUserOpinions;

    @OneToMany(mappedBy="userAuthor", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<EventPlaceOpinion> userAuthorEventPlaceOpinions;

    @OneToMany(mappedBy="id.user", cascade= {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Participation> userParticipations;

    public User(String username, String email, String password, String name, String surname, boolean archived, String phoneNumber,
                boolean enabled, String photoLink, String description) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.archived = archived;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
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
                ", isEnabled=" + enabled +
                '}';
    }
}
