package com.codecool.funfacts.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

@RestController
public class AvatarApi {

    @GetMapping("/randomavatar")
    public String randomJoke() throws IOException {

        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;

        final String uri = "https://api.adorable.io/avatars/" + randomNumber + "/abott@adorable.io.png";

        return uri;
    }

}
