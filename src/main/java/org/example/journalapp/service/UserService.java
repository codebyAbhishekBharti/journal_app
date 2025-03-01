package org.example.journalapp.service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.example.journalapp.entity.User;
import org.example.journalapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private static  final Logger logger = LoggerFactory.getLogger(UserService.class);


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean saveNewEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("Error occured for {} : ",user.getUserName(),e);
            log.warn("Error saving entry");
            log.info("Error saving entry");
            log.trace("Error saving entry");
            log.debug("Error saving entry");
            return false;
        }
    }

    public void saveEntry(User user) {
        userRepository.save(user);
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

    public boolean saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        return userRepository.save(user)!=null;
    }
}