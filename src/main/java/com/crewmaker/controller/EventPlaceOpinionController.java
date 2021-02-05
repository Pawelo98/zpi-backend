package com.crewmaker.controller;

import com.crewmaker.dto.EventPlaceOpinionDTO;
import com.crewmaker.entity.Event;
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

    @PostMapping("/addeventplaceopinion")
    public ResponseEntity<?> registerOpinion (@Valid @RequestBody EventPlaceOpinionRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        EventPlace eventPlace = eventPlaceRepository.findByEventPlaceId(request.getEventID());

        EventPlaceOpinion eventPlaceOpinion = new EventPlaceOpinion(eventPlace,user,request.getTitle(),request.getMessage(),request.getGrade());

        eventPlaceOpinionRepository.save(eventPlaceOpinion);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(true, "Opinion added"));
    }

    @GetMapping("/geteventplaceopinions")
    public List<EventPlaceOpinionDTO> getEventPlaceOpinions (@RequestParam int eventplaceID){
        return eventPlaceOpinionRepository.findAllByEventPlaceEventPlaceId(eventplaceID).stream().map(el -> new EventPlaceOpinionDTO(el)).collect(Collectors.toList());
    }
}
