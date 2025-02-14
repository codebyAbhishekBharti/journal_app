package org.example.journalapp.controllers;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.entity.User;
import org.example.journalapp.service.JournalEntryService;
import org.example.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findUserByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/id/{userName}/{journalId}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable String userName, @PathVariable ObjectId journalId){
        Optional<JournalEntry> entry = journalEntryService.findById(userName,journalId);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/id/{userName}/{journalId}")
    public ResponseEntity<JournalEntry> deleteEntry(@PathVariable String userName, @PathVariable ObjectId journalId){
        Optional<JournalEntry> entry = journalEntryService.findById(userName,journalId);
        if(entry.isPresent()){
            journalEntryService.deleteById(userName,journalId);
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/id/{userName}/{journalId}")
    public ResponseEntity<?> updateEntry(@PathVariable String userName, @PathVariable ObjectId journalId, @RequestBody JournalEntry entry){
        Optional<JournalEntry> oldEntry = journalEntryService.findById(userName,journalId);
        if(oldEntry.isPresent()){
            entry.setId(oldEntry.get().getId());
            entry.setDate(oldEntry.get().getDate());
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
