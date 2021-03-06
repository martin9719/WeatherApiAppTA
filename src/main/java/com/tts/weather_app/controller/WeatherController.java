package com.tts.weather_app.controller;

import com.tts.weather_app.model.Request;
import com.tts.weather_app.model.Response;
import com.tts.weather_app.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String getIndex(Model model) {
        // Response response = weatherService.getForecast("43210");
        // model.addAttribute("data", response);
        model.addAttribute("request", new Request());
        model.addAttribute("zipcodes", weatherService.getLastTen());
        return "index";
    }

    @PostMapping
    public String postIndex(Request request, Model model) {
        Response data = weatherService.getForecast(request.getZipCode());
        model.addAttribute("data", data);
        model.addAttribute("zipcodes", weatherService.getLastTen());
        return "index";
    }
}
