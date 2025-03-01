package org.example.journalapp.cache;

import jakarta.annotation.PostConstruct;
import org.example.journalapp.entity.ConfigJournalApp;
import org.example.journalapp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalApp> all = configJournalAppRepository.findAll();
        for(ConfigJournalApp configJournalApp:all){
            appCache.put(configJournalApp.getKey(),configJournalApp.getValue());
        }
    }
}
