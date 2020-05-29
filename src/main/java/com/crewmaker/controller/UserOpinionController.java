package com.crewmaker.controller;

import com.crewmaker.dto.UserOpinionDTO;
import com.crewmaker.entity.*;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.UserOpinionRepository;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.NewEventPlaceRequest;
import com.crewmaker.reqbody.NewUserOpinionRequest;
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
    public ResponseEntity<?> addNewUserOpinion(@RequestBody NewUserOpinionRequest newUserOpinionRequest) {
        System.out.println(newUserOpinionRequest.toString());
        User author = userRepository.findByUsername(newUserOpinionRequest.getUserAuthor())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", newUserOpinionRequest.getUserAuthor()));

        User about = userRepository.findByUsername(newUserOpinionRequest.getUserAbout())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", newUserOpinionRequest.getUserAuthor()));

        UserOpinion userOpinion = userOpinionRepository.findByUserAboutUsernameAndUserAuthorUsername(
                                    newUserOpinionRequest.getUserAbout(), newUserOpinionRequest.getUserAuthor());

        if(userOpinion == null) {
            userOpinion = new UserOpinion(author, about, newUserOpinionRequest.getTitle(),
                    newUserOpinionRequest.getMessage(), newUserOpinionRequest.getGrade());
        } else {
            userOpinion.setTitle(newUserOpinionRequest.getTitle());
            userOpinion.setMessage(newUserOpinionRequest.getMessage());
            userOpinion.setGrade(newUserOpinionRequest.getGrade());
        }

        UserOpinion result = userOpinionRepository.save(userOpinion);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUserOpinionId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "New opinion about user was added"));
    }
}
