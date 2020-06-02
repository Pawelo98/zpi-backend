package com.crewmaker.controller;


import com.crewmaker.dto.EventDTO;
import com.crewmaker.dto.EventPlaceDTO;
import com.crewmaker.entity.*;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.*;
import com.crewmaker.reqbody.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

@RestController
@RequestMapping("/api")
public class EventPlaceController {

    @Autowired
    EventPlaceRepository eventPlaceRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SportsCategoryRepository sportsCategoryRepository;
    @Autowired
    EventPlaceSportsCategoryRepository eventPlaceSportsCategoryRepository;
    @Autowired
    CyclePeriodRepository cyclePeriodRepository;

    @PostMapping("/newEventPlace")
    public ResponseEntity<?> addNewEventPlace(@RequestBody NewEventPlaceRequest newEventPlace) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        EventPlaceImage newImg = null;

        if(newEventPlace.getEventPlaceImage() != null){
            String prefixType = newEventPlace.getEventPlaceImage().split(";")[0];
            String type = prefixType.split(":")[1];
            String base64Image = newEventPlace.getEventPlaceImage().split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            newImg = new EventPlaceImage("newEventPlaceImage", type,
                    compressBytes(imageBytes));
        }


        EventPlace eventPlace = new EventPlace(user, newEventPlace.getEventPlaceName(), newEventPlace.getEventPlaceDescription(), newEventPlace.getEventPlaceCity(),
                newEventPlace.getEventPlacePostalCode(), newEventPlace.getEventPlaceStreet(), newEventPlace.getEventPlaceStreetNumber(), newImg);

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
                                          @RequestParam(required = true, defaultValue = "10" , name = "sorting") String sorting,
                                          @RequestParam(name = "city") String city){

        String[] filters = sorting.split("\\_");
        Page<EventPlace> page;

        if(city.equals("")) {
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
                case "ARCH": {
                    page = eventPlaceRepository.findAllByIsArchivedIsTrue(
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }
                case "NOTARCH": {
                    page = eventPlaceRepository.findAllByIsArchivedIsFalse(
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }

                default: {
                    page = eventPlaceRepository.findAll(
                            PageRequest.of(activePage-1, size, Sort.by(Sort.Direction.ASC, "eventPlaceId")));
                    break;
                }
            }
        }else {
            switch(filtering){
                case "ALL": {
                    page = eventPlaceRepository.findAllByCityIgnoreCaseContaining(city,
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }
                case "ACC": {
                    page = eventPlaceRepository.findAllByIsAcceptedIsTrueAndCityIgnoreCaseContaining(city,
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }
                case "NOTACC": {
                    page = eventPlaceRepository.findAllByIsAcceptedIsFalseAndCityIgnoreCaseContaining(city,
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }
                case "ARCH": {
                    page = eventPlaceRepository.findAllByIsArchivedIsTrueAndCityIgnoreCaseContaining(city,
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }
                case "NOTARCH": {
                    page = eventPlaceRepository.findAllByIsArchivedIsFalseAndCityIgnoreCaseContaining(city,
                            PageRequest.of(activePage - 1, size, Sort.by(Sort.Direction.valueOf(filters[0]), filters[1])));
                    break;
                }

                default: {
                    page = eventPlaceRepository.findAll(
                            PageRequest.of(activePage-1, size, Sort.by(Sort.Direction.ASC, "eventPlaceId")));
                    break;
                }
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

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    @GetMapping("/acceptEventPlace")
    ResponseEntity<?> acceptEventPlace(@RequestParam(required = true, name = "eventPlaceID") int eventPlaceID){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        EventPlace eventPlace = eventPlaceRepository.findById(eventPlaceID)
                    .orElseThrow(() -> new ResourceNotFoundException("EventPlace", "eventPlaceId", eventPlaceID));
        eventPlace.setAccepted(true);
        eventPlace.setUserAccepting(user);
        eventPlaceRepository.save(eventPlace);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/eventPlace/{eventPlaceId}")
                .buildAndExpand(eventPlace.getEventPlaceId()).toUri();

        return ResponseEntity.created(location).body(new EventPlaceAcceptedResponse(true,  eventPlace.getAccepted(), user.getUsername(), "User data changed successfully"));
    }

    @GetMapping("/archiveEventPlace")
    ResponseEntity<?> deleteEventPlace(@RequestParam(required = true, name = "eventPlaceID") int eventPlaceID,
                                       @RequestParam(required = true, name = "currentArchiveStatus") boolean currentArchiveStatus){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        EventPlace eventPlace = eventPlaceRepository.findById(eventPlaceID)
                .orElseThrow(() -> new ResourceNotFoundException("EventPlace", "eventPlaceId", eventPlaceID));
        eventPlace.setArchived(!currentArchiveStatus);
        eventPlaceRepository.save(eventPlace);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/eventPlace/{eventPlaceId}")
                .buildAndExpand(eventPlace.getEventPlaceId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Event place archive status has been changed"));
    }


    @GetMapping("/countEventPlaceEvents")
    ResponseEntity<?> getEventPlaceEventsCount(@RequestParam(required = true, name = "eventPlaceID") int eventPlaceID ){
        EventPlace eventPlace = eventPlaceRepository.findById(eventPlaceID)
                .orElseThrow(() -> new ResourceNotFoundException("EventPlace", "eventPlaceId", eventPlaceID));

       int calculate = eventRepository.countAllByEventPlace(eventPlace);
       int calculateBefore = eventRepository.countAllByEventPlaceAndDateBefore(eventPlace, new Date());
       int calculateAfter = eventRepository.countAllByEventPlaceAndDateAfter (eventPlace, new Date());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/eventPlace/{eventPlaceId}")
                .buildAndExpand(eventPlace.getEventPlaceId()).toUri();

        return ResponseEntity.ok().body(new EventPlaceStatisticsResponse(calculate, calculateBefore, calculateAfter));
    }

    @GetMapping("/eventPlaces")
    List<EventPlaceDTO> getEventPlaces() {
        return eventPlaceRepository.findAll().stream().limit(20).map(eventPlace ->  new EventPlaceDTO(eventPlace)).collect(Collectors.toList());
    }

    @GetMapping("/cyclics")
    List<CyclePeriod> getCyclics() {
        return cyclePeriodRepository.findAll();
    }
}


