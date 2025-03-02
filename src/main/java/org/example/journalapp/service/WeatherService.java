package org.example.journalapp.service;

import org.example.journalapp.api.response.WeatherResponse;
import org.example.journalapp.cache.AppCache;
import org.example.journalapp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${weather.api.key}")
    private String api_key;
    @Autowired
    private AppCache appCache;
    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_"+city,WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }
        else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString())
                    .replace(Placeholders.API_KEY,api_key)
                    .replace(Placeholders.CITY,city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body= response.getBody();
            if(body!=null){
                redisService.set("weather_of_"+city,body,300);
            }
            return body;
        }
    }

}
