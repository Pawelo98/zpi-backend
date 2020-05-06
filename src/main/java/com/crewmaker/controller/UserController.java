package com.crewmaker.controller;

import com.crewmaker.authentication.AvailabilityConfirmation;
import com.crewmaker.authentication.CurrentUser;
import com.crewmaker.authentication.UserPrincipal;
import com.crewmaker.authentication.UserSummary;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.model.UserProfile.UserProfileUser;
import com.crewmaker.entity.User;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.UserUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @PostMapping("/updateUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUsername(userUpdateRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", userUpdateRequest.getUsername()));
        // Creating user's account
        user.setEmail(userUpdateRequest.getEmail());
        user.setName(userUpdateRequest.getName());
        user.setSurname(userUpdateRequest.getSurname());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setDescription(userUpdateRequest.getDescription());

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User data changed successfully"));
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
