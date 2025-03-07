package org.example.journalapp.scheduler;

import org.example.journalapp.controllers.AdminController;
import org.example.journalapp.entity.JournalEntry;
import org.example.journalapp.entity.User;
import org.example.journalapp.enums.Sentiment;
import org.example.journalapp.repository.UserRepositoryImpl;
import org.example.journalapp.service.EmailService;
import org.example.journalapp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {
    @Autowired
    EmailService emailService;
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    AdminController adminController;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntry = user.getJournalEntries();
            List<Sentiment> filteredJournalEntries = journalEntry.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment())
                    .collect(Collectors.toList());
            Map<Sentiment,Integer> sentimentCountr = new HashMap<>();
            for(Sentiment sentiment:filteredJournalEntries){
                if(sentiment!=null)
                sentimentCountr.put(sentiment,sentimentCountr.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiment,Integer> entry:sentimentCountr.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment of last 7 days",mostFrequentSentiment.toString());
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        adminController.clearAppCache();
    }
}
