package org.example.journalapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.journalapp.entity.User;
import org.example.journalapp.service.UserService;
import org.example.journalapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtutil;

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.saveNewEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
             String jwt = jwtutil.generateToken(userDetails.getUsername());
             return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error occured: ",e);
            return new ResponseEntity<>("Incorrect UserName or password",HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "Everything is working fine";
    }
}
