package com.crewmaker.controller;

import com.crewmaker.authentication.AvailabilityConfirmation;
import com.crewmaker.reqbody.LoginRequest;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/login/test")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) throws Exception{
        //UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), passwordEncoder.encode(loginRequest.getPassword()));
        //Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext();
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        return ResponseEntity.ok(userDetails);
    }


    @GetMapping("/api/user/checkUsernameAvailability")
    public AvailabilityConfirmation checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new AvailabilityConfirmation(isAvailable);
    }

    @GetMapping("/api/user/checkEmailAvailability")
    public AvailabilityConfirmation checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new AvailabilityConfirmation(isAvailable);
    }
}
