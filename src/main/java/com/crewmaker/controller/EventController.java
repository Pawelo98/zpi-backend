package com.crewmaker.controller;

import com.crewmaker.dto.EventDTO;
import com.crewmaker.entity.*;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.*;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.NewEventPlaceRequest;
import com.crewmaker.reqbody.NewEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CyclePeriodRepository cyclePeriodRepository;
    @Autowired
    private EventStatusRepository eventStatusRepository;
    @Autowired
    private EventPlaceRepository eventPlaceRepository;
    @Autowired
    private SportsCategoryRepository sportsCategoryRepository;

    //baeldung requestparams zeby parametryzowac tutaj jaki event i przeslac sobie id sportscategory a nie string.
   /* @GetMapping("/api/searchevents")
    List<EventDTO> searchEvents(@RequestParam(name = "categoryid") int categoryID){
        return eventRepository.findAllBySportsCategorySportsCategoryId(categoryID)
                .stream().limit(10).map(event -> new EventDTO(event)).collect(Collectors.toList());
    }*/
    @GetMapping("/api/searchevents")
    List<EventDTO> searchEvents(@RequestParam(name = "categoryid") int categoryID,
                                @RequestParam  @DateTimeFormat(pattern = "dd-MM-yyyy") Date eventDate){

        //System.out.println(eventDate.toString());
        /*return eventRepository.findAllBySportsCategorySportsCategoryId(categoryID)
                .stream().limit(10).map(event -> new EventDTO(event)).collect(Collectors.toList());
*/
        return eventRepository.findAllByDateAfterAndAndSportsCategorySportsCategoryIdOrderByDate(
                eventDate
                ,categoryID)
                .stream().limit(10).map(event ->  new EventDTO(event)).collect(Collectors.toList());
        /*return eventRepository.findAllByDateAfterAndEventTimeAfterAndSportsCategorySportsCategoryIdOrderByDateAscEventTimeAsc(eventDate,eventTime,categoryID)
                .stream().limit(10).map(e -> new EventDTO(e)).collect(Collectors.toList());*/
    }

    @GetMapping("/api/event")
    EventDTO findOneEvent (@RequestParam(name = "eventId") int eventId) {
        return new EventDTO(eventRepository.findByEventId(eventId));
    }

    @GetMapping("/api/test")
    String test(){
        return "TEST";
    }

    @GetMapping("api/myevents/{username}")
    List<EventDTO> getMyEvents(@PathVariable(value = "username") String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return eventRepository.findAllByEventParticipationsIdUser(user)
                .stream().map(event -> new EventDTO(event)).collect(Collectors.toList());

    }

    @GetMapping("/api/counteventsparticipants")
    long countParticipants(@RequestParam int eventID){
        return eventRepository.countAllByEventParticipationsIdEvent(eventRepository.findByEventId(eventID));
    }

    @GetMapping("api/eventuser")
    String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();

    }

    @PostMapping("api/newEvent")
    public ResponseEntity<?> addNewEvent(@RequestBody NewEventRequest newEvent) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        CyclePeriod cyclePeriod = cyclePeriodRepository.findByCyclePeriodId(newEvent.getCycleId());
        EventStatus eventStatus = eventStatusRepository.findByEventStatusId(1);
        EventPlace eventPlace = eventPlaceRepository.findByEventPlaceId(newEvent.getEventPlaceId());
        SportsCategory sportsCategory = sportsCategoryRepository.findBySportsCategoryId(newEvent.getSportCategoryId());

        Event event = new Event(cyclePeriod, eventStatus, eventPlace, sportsCategory, newEvent.getEventName(),
                newEvent.getEventDescription(), newEvent.getEventDate(), newEvent.getMaxPlayers(), newEvent.isCyclic(),
                newEvent.getEventTime(), newEvent.getEventDuration(), user);

        Event result = eventRepository.save(event);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getEventId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "New event added successfully"));
    }
}
