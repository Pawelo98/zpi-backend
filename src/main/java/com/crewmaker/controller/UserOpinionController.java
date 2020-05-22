package com.crewmaker.controller;

import com.crewmaker.dto.UserOpinionDTO;
import com.crewmaker.repository.UserOpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserOpinionController {
    @Autowired
    private UserOpinionRepository userOpinionRepository;

    @GetMapping("/useropinions")
    public List<UserOpinionDTO> getUserOpinions(@RequestParam String username){
        return userOpinionRepository.findAllByUserAboutUsername(username).stream()
                .map(opinion -> new UserOpinionDTO(opinion)).collect(Collectors.toList());
    }
}
