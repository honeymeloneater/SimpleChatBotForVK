package com.example.SimpleVkBot.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.Random;

@Configuration
public class ChatConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public HttpHeaders httpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }
    @Bean
    public  HttpEntity<String> httpEntity(){
        return new HttpEntity<>(httpHeaders());
    }
    @Bean
    public Gson gson(){
        return new Gson();
    }
    @Bean
    public Random random(){
        return new Random();
    }
}
