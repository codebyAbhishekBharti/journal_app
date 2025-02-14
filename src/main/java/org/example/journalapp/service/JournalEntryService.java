package org.example.journalapp.service;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public boolean saveEntry(JournalEntry entry) {
        return journalEntryRepository.save(entry)!=null;
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public JournalEntry deleteById(ObjectId id) {
        Optional<JournalEntry> entry = findById(id);
        if(entry.isPresent()){
            journalEntryRepository.deleteById(id);
            return entry.get();
        }
        else return null;
    }
}