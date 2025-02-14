package org.example.journalapp.service;

import org.bson.types.ObjectId;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.entity.User;
import org.example.journalapp.repository.JournalEntryRepository;
import org.example.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean saveEntry(User entry) {
        return userRepository.save(entry)!=null;
    }

    public Optional<?> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User deleteById(ObjectId id) {
        Optional<?> entry = findById(id);
        if(entry.isPresent()){
            userRepository.deleteById(id);
            return (User) entry.get();
        }
        else return null;
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }
}