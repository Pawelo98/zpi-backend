package com.crewmaker.controller;

import com.crewmaker.dto.EventDTO;
import com.crewmaker.entity.Event;
import com.crewmaker.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    //baeldung requestparams zeby parametryzowac tutaj jaki event i przeslac sobie id sportscategory a nie string.
    @GetMapping("/api/searchevents")
    List<EventDTO> searchEvents(){
        return eventRepository.findBySportsCategorySportCategoryName("Koszykówka").stream().limit(10).map(event -> new EventDTO(event)).collect(Collectors.toList());
    }

    @GetMapping("/api/event")
    EventDTO findoneevent () {
        return new EventDTO(eventRepository.findByEventId(6));
    }


    @GetMapping("/api/test")
    String test(){
        return "TEST";
    }
}
