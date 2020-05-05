package com.crewmaker.controller;

import com.crewmaker.entity.User;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.model.UserProfile.UserProfileUser;
import com.crewmaker.repository.EventRepository;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.UserUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    /*
    @GetMapping("/event/future/{id}")
    public List<Event> getUserFutureEvents(@PathVariable int id) {
        //return eventRepository.findFutureEventsByUsername(username);
        return eventRepository.findByEventId(1);
    }

    @GetMapping("/event/past/{id}")
    public List<Event> getUserPastEvents(@PathVariable int id) {
        return eventRepository.findByEventId(1);
    }
    */
}
