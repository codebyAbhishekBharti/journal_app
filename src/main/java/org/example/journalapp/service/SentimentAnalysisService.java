package org.example.journalapp.service;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {
    public String getSentiment(String entry) {
        return "positive";
    }
}
