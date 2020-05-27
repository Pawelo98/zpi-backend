package com.crewmaker.controller;

import com.crewmaker.dto.SportsCategoryDTO;
import com.crewmaker.entity.SportsCategory;
import com.crewmaker.repository.SportsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SportsCategoryController {
    @Autowired
    SportsCategoryRepository sportsCategoryRepository;

    @GetMapping("/sportscategories")
    List<SportsCategoryDTO> getAllSportsCategory() {
        return sportsCategoryRepository.findAll().stream().map(sportsCategory -> new SportsCategoryDTO(sportsCategory)).collect(Collectors.toList());
    }

    @GetMapping("/sportscategoriesplaces")
    List<SportsCategoryDTO> getAllSportsCategoryByEventID(@RequestParam(name = "eventPlaceID") int eventPlaceID) {
        return sportsCategoryRepository.findByEventPlaceId(eventPlaceID);
    }
}
