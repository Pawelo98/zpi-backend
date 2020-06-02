package com.crewmaker.controller;

import com.crewmaker.dto.EventPlaceOpinionDTO;
import com.crewmaker.entity.EventPlace;
import com.crewmaker.entity.EventPlaceOpinion;
import com.crewmaker.entity.User;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.EventPlaceOpinionRepository;
import com.crewmaker.repository.EventPlaceRepository;
import com.crewmaker.repository.EventRepository;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.EventPlaceOpinionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api")
public class EventPlaceOpinionController {
    @Autowired
    EventPlaceOpinionRepository eventPlaceOpinionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventPlaceRepository eventPlaceRepository;

    @PostMapping("/addEventPlaceOpinion")
    public ResponseEntity<?> registerOpinion (@Valid @RequestBody EventPlaceOpinionRequest request){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        EventPlace eventPlace = Optional.ofNullable(eventPlaceRepository.findByEventPlaceId(request.getEventPlaceAbout()))
                .orElseThrow(() -> new ResourceNotFoundException("EventPlace", "eventPlaceId", request.getEventPlaceAbout()));

        EventPlaceOpinion eventPlaceOpinion = eventPlaceOpinionRepository.findByEventPlaceAndUserAuthor(
                eventPlace, user);

        if(eventPlaceOpinion == null) {
            eventPlaceOpinion = new EventPlaceOpinion(eventPlace,user,request.getTitle(),request.getMessage(),request.getGrade());
        } else {
            eventPlaceOpinion.setTitle(request.getTitle());
            eventPlaceOpinion.setMessage(request.getMessage());
            eventPlaceOpinion.setGrade(request.getGrade());
        }


        eventPlaceOpinionRepository.save(eventPlaceOpinion);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(true, "Opinion added"));
    }

    @GetMapping("/eventOpinion")
    public EventPlaceOpinionDTO getUserOpinion(@RequestParam int eventPlaceID, @RequestParam String currentUser){

        EventPlace eventPlace = eventPlaceRepository.findByEventPlaceId(eventPlaceID);

        EventPlaceOpinion opinion = eventPlaceOpinionRepository.findByEventPlaceAndUserAuthorUsername(eventPlace, currentUser);
        if(opinion != null) {
            return new EventPlaceOpinionDTO(opinion);
        } else {
            return null;
        }
    }

    @GetMapping("/getEventPlaceOpinions")
    public List<EventPlaceOpinionDTO> getEventPlaceOpinions (@RequestParam int eventPlaceID){
        return eventPlaceOpinionRepository.findAllByEventPlaceEventPlaceId(eventPlaceID).stream().map(el -> new EventPlaceOpinionDTO(el)).collect(Collectors.toList());
    }
}
