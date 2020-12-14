package com.tts.weather_app.service;

import java.util.ArrayList;
import java.util.List;

import com.tts.weather_app.model.Response;
import com.tts.weather_app.model.ZipCode;
import com.tts.weather_app.repository.ZipCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${api_key}")
    private String apiKey;

    @Autowired
    private ZipCodeRepository zipCodeRepository;

    public Response getForecast(String zipCode) {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&units=imperial&appid="
                + apiKey;
        Response response = new Response();
        RestTemplate restTemplate = new RestTemplate();
        // List<ZipCode> zipCodeList = zipCodeRepository.findAll();
        // try {
        // return restTemplate.getForObject(url, Response.class);
        // }
        // catch (HttpClientErrorException ex) {
        // Response response = new Response();
        // response.setName("error");
        // return response;
        // }
        try {
            response = restTemplate.getForObject(url, Response.class);
            ZipCode zip = new ZipCode();
            zip.setZipcode(zipCode);
            // zipCodeList.add(zip);
            saveZip(zip);
        } catch (HttpClientErrorException ex) {

            response.setName("error");

        }
        return response;
    }

    public void saveZip(ZipCode zipCode) {
        zipCodeRepository.save(zipCode);
    }

    public List<ZipCode> getLastTen() {
        List<ZipCode> zipCodeList = zipCodeRepository.findAllByOrderByCreatedAtDesc();
        List<ZipCode> lastTenZip = new ArrayList<>();
        zipLoop: for (ZipCode zip : zipCodeList) {
            if (lastTenZip.size() < 10) {
                lastTenZip.add(zip);
            } else {
                break zipLoop;
            }
        }
        return lastTenZip;
    }

}
