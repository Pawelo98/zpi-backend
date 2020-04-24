package com.crewmaker.controller;

import com.crewmaker.authentication.AvailabilityConfirmation;
import com.crewmaker.authentication.CurrentUser;
import com.crewmaker.authentication.UserPrincipal;
import com.crewmaker.authentication.UserSummary;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.model.UserProfile.UserProfileUser;
import com.crewmaker.entity.User;
import com.crewmaker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/users/{username}")
    public UserProfileUser getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfileUser userProfile = new UserProfileUser(user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getPhotoLink(), user.getDescription(), user.getName(), user.getSurname());

        return userProfile;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public AvailabilityConfirmation checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new AvailabilityConfirmation(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public AvailabilityConfirmation checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new AvailabilityConfirmation(isAvailable);
    }
}
