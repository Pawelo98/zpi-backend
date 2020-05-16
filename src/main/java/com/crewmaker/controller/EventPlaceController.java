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
import com.crewmaker.reqbody.EventPlaceResponse;
import com.crewmaker.reqbody.NewEventPlaceRequest;
import com.crewmaker.reqbody.PagingOptionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @PostMapping("/newEventPlace")
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

    @GetMapping("/getEventPlace")
    Page<EventPlaceResponse> searchEvents(@RequestParam(required = true, defaultValue = "0", name = "activePage") int activePage,
                                          @RequestParam(required = true, defaultValue = "10" , name = "size") int size,
                                          @RequestParam(required = true, defaultValue = "ALL" , name = "filtering") String filtering,
                                          @RequestParam(required = true, defaultValue = "10" , name = "sorting") String sorting){

        String[] filters = sorting.split("\\_");
        Page<EventPlace> page;

        switch(filtering){
            case "ALL": {
                page = eventPlaceRepository.findAll(
                        PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                break;
            }
            case "ACC": {
                page = eventPlaceRepository.findAllByIsAcceptedIsTrue(
                        PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                break;
            }
            case "NOTACC": {
                page = eventPlaceRepository.findAllByIsAcceptedIsFalse(
                        PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                break;
            }
            default: {
                page = eventPlaceRepository.findAll(
                        PageRequest.of(activePage-1, size, Sort.by(Sort.Direction.ASC, "eventPlaceId")));
                break;
            }
        }

        Page<EventPlaceResponse> eventPlaceResponse = page.map(new Function<EventPlace, EventPlaceResponse>() {
            @Override
            public EventPlaceResponse apply(EventPlace eventPlace) {
                return new EventPlaceResponse(eventPlace);
            }
        });

        return eventPlaceResponse;
    }

}


