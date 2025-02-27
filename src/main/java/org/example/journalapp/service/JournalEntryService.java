package org.example.journalapp.service;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.entity.User;
import org.example.journalapp.repository.JournalEntryRepository;
import org.example.journalapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    @Transactional
    public void saveEntry(JournalEntry entry, String userName) {
        try{
            User user = userService.findUserByUserName(userName);
            entry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(entry);
            if(!user.getJournalEntries().contains(saved)){
                user.getJournalEntries().add(saved);
                userService.saveEntry(user);
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Error saving entry");
        }

    }

    public Optional<JournalEntry> findById(String userName, ObjectId journalId) {
        User user = userService.findUserByUserName(userName);
        for(JournalEntry entry: user.getJournalEntries()){
            if(entry.getId().equals(journalId) && journalEntryRepository.findById(entry.getId()).isPresent()){
                return Optional.of(entry);
            }
        }
        return Optional.empty();
    }

    public JournalEntry deleteById(String userName, ObjectId journalId) {
        User user = userService.findUserByUserName(userName);
        Optional<JournalEntry> entry = findById(userName,journalId);
        if(entry.isPresent()){
            user.getJournalEntries().remove(entry.get());
            userService.saveEntry(user);
            journalEntryRepository.deleteById(journalId);
            return entry.get();
        }
        else return null;
    }
}