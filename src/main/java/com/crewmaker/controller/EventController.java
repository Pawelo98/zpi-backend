package com.crewmaker.controller;

import com.crewmaker.dto.EventDTO;
import com.crewmaker.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    //baeldung requestparams zeby parametryzowac tutaj jaki event i przeslac sobie id sportscategory a nie string.
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
    EventDTO findoneevent () {
        return new EventDTO(eventRepository.findByEventId(6));
    }


    @GetMapping("/api/test")
    String test(){
        return "TEST";
    }
}
