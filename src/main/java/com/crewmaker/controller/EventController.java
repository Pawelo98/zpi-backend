package com.crewmaker.controller;

import com.crewmaker.dto.EventDTO;
import com.crewmaker.entity.Event;
import com.crewmaker.entity.User;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.EventRepository;
import com.crewmaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

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

}
