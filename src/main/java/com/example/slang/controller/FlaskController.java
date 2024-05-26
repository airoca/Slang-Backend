package com.example.slang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/flask")
public class FlaskController {

    private final String FLASK_SERVER_URL = "http://localhost:5000"; // Flask 서버의 URL

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/video_sports")
    @ResponseBody
    public ResponseEntity<String> getVideoSportsFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/video_sports", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/video_korean")
    @ResponseBody
    public ResponseEntity<String> getVideoKoreanFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/video_korean", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/video_animals")
    @ResponseBody
    public ResponseEntity<String> getVideoAnimalsFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/video_animals", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/video_food")
    @ResponseBody
    public ResponseEntity<String> getVideoFoodFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/video_food", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/prediction_sports")
    @ResponseBody
    public ResponseEntity<String> getPredictionSportsFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/prediction_sports", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/prediction_korean")
    @ResponseBody
    public ResponseEntity<String> getPredictionKoreanFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/prediction_korean", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/prediction_animals")
    @ResponseBody
    public ResponseEntity<String> getPredictionAnimalsFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/prediction_animals", String.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/prediction_food")
    @ResponseBody
    public ResponseEntity<String> getPredictionFoodFromFlask() {
        String result = restTemplate.getForObject(FLASK_SERVER_URL + "/prediction_food", String.class);
        return ResponseEntity.ok(result);
    }
}
