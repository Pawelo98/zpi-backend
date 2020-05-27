package com.crewmaker.controller;

import com.crewmaker.dto.UserOpinionDTO;
import com.crewmaker.entity.*;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.UserOpinionRepository;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.NewEventPlaceRequest;
import com.crewmaker.reqbody.UserOpinionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserOpinionController {
    @Autowired
    private UserOpinionRepository userOpinionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/useropinions")
    public List<UserOpinionDTO> getUserOpinions(@RequestParam String username, @RequestParam String currentUser){
        return userOpinionRepository.findAllByUserAboutUsernameAndUserAuthorUsernameNot(username, currentUser).stream()
                .map(opinion -> new UserOpinionDTO(opinion)).collect(Collectors.toList());
    }

    @GetMapping("/useropinion")
    public UserOpinionDTO getUserOpinion(@RequestParam String username, @RequestParam String currentUser){
        UserOpinion opinion = userOpinionRepository.findByUserAboutUsernameAndUserAuthorUsername(username, currentUser);
        if(opinion != null) {
            return new UserOpinionDTO(opinion);
        } else {
            return null;
        }
    }

    @PostMapping("/newUserOpinion")
    public ResponseEntity<?> addNewUserOpinion(@RequestBody UserOpinionRequest userOpinionRequest) {

        User author = userRepository.findByUsername(userOpinionRequest.getUserAuthor())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", userOpinionRequest.getUserAuthor()));

        User about = userRepository.findByUsername(userOpinionRequest.getUserAbout())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", userOpinionRequest.getUserAuthor()));

        UserOpinion userOpinion = new UserOpinion(author, about, userOpinionRequest.getTitle(),
                                        userOpinionRequest.getMessage(), userOpinionRequest.getGrade());

        UserOpinion result = userOpinionRepository.save(userOpinion);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUserOpinionId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "New opinion about user was added"));
    }
}
