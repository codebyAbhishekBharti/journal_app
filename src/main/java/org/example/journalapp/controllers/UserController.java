package org.example.journalapp.controllers;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.entity.User;
import org.example.journalapp.service.JournalEntryService;
import org.example.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping
    public ResponseEntity<?> updateEntry(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userInDb = userService.findUserByUserName(name);
        System.out.println(userInDb);
        if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEntry(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User userInDb = userService.findUserByUserName(name);
        if(userInDb!=null){
            userService.deleteById(userInDb.getId());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
