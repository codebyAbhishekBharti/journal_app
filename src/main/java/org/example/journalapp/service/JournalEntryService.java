package org.example.journalapp.service;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.entity.User;
import org.example.journalapp.repository.JournalEntryRepository;
import org.example.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveEntry(JournalEntry entry, String userName) {
        User user = userService.findUserByUserName(userName);
        entry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(entry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
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