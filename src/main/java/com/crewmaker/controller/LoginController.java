package com.crewmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @RequestMapping(value={"/login"}, method=RequestMethod.GET)
    public String showLoginPage() {
        System.out.println("==== in greeting ====");
        return "login";
    }
}