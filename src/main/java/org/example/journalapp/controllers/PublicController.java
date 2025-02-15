package org.example.journalapp.controllers;

import org.example.journalapp.entity.User;
import org.example.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void createEntry(@RequestBody User user){
        userService.saveEntry(user);
    }

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "Everything is working fine";
    }
}
