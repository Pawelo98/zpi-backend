package com.crewmaker.controller;


import com.crewmaker.entity.EventPlace;
import com.crewmaker.entity.EventPlaceSportsCategory;
import com.crewmaker.entity.SportsCategory;
import com.crewmaker.entity.User;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.EventPlaceRepository;
import com.crewmaker.repository.EventPlaceSportsCategoryRepository;
import com.crewmaker.repository.SportsCategoryRepository;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.NewEventPlaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class EventPlaceController {

    @Autowired
    EventPlaceRepository eventPlaceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SportsCategoryRepository sportsCategoryRepository;
    @Autowired
    EventPlaceSportsCategoryRepository eventPlaceSportsCategoryRepository;

    @PostMapping("/neweventplace")
    public ResponseEntity<?> addNewEventPlace(@RequestBody NewEventPlaceRequest newEventPlace) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        EventPlace eventPlace = new EventPlace(user, newEventPlace.getEventPlaceName(), newEventPlace.getEventPlaceDescription(), newEventPlace.getEventPlaceCity(),
                newEventPlace.getEventPlacePostalCode(), newEventPlace.getEventPlaceStreet(), newEventPlace.getEventPlaceStreetNumber(), null);

        EventPlace result = eventPlaceRepository.save(eventPlace);

        for(int sportCategoryId: newEventPlace.getSportsCategory()){
            SportsCategory sportsCategory = sportsCategoryRepository.findById(sportCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Sports Category", "id", sportCategoryId));

            EventPlaceSportsCategory newEventPlaceSportsCategory = new EventPlaceSportsCategory(result, sportsCategory);
            eventPlaceSportsCategoryRepository.save(newEventPlaceSportsCategory);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEventPlaceId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "New event place added to verification successfully"));
    }


}


