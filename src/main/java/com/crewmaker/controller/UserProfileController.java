package com.crewmaker.controller;

import com.crewmaker.entity.User;
import com.crewmaker.model.UserProfile.UserProfileEvent;
import com.crewmaker.model.UserProfile.UserProfileUser;
import com.crewmaker.repository.EventRepository;
import com.crewmaker.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/userProfile")
public class UserProfileController {
    private UserRepository userRepository;
    private EventRepository eventRepository;

    public UserProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public UserProfileUser getUserProfiles(@PathVariable String username) {
        return userRepository.findByUsernameUserProfile(username);
    }

    @GetMapping("/event/future/{username}")
    public Collection<UserProfileEvent> getUserFutureEvents(@PathVariable String username) {
        //return eventRepository.findFutureEventsByUsername(username);
        return eventRepository.findPastEventsByUsername(username);
    }

    @GetMapping("/event/past/{username}")
    public Collection<UserProfileEvent> getUserPastEvents(@PathVariable String username) {
        return eventRepository.findPastEventsByUsername(username);
    }
}
