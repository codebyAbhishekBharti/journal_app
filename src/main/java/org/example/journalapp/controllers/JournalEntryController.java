package org.example.journalapp.controllers;

import org.example.journalapp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(), entry);
        return true;
    }
    @GetMapping("/id/{id}")
    public JournalEntry getEntry(@PathVariable long id){
        return journalEntries.get(id);
    }
    @DeleteMapping("/id/{id}")
    public JournalEntry deleteEntry(@PathVariable long id){
        return journalEntries.remove(id);
    }
    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable long id, @RequestBody JournalEntry entry){
        journalEntries.put(id, entry);
        return journalEntries.get(id);
    }
}
