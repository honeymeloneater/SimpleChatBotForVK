package com.example.SimpleVkBot.service;


import com.example.SimpleVkBot.model.VkMessage;
import com.example.SimpleVkBot.model.VkObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Random;

@Service
@Slf4j
public class MessageService {

    @Value("${vk.api.url.sendmsg}")
    private  String vkApiMethod;
    private final RestTemplate restTemplate;
    private final HttpEntity<String> entity;
    private final Random random;

    public MessageService(RestTemplate restTemplate, HttpEntity<String> entity, Random random) {
        this.restTemplate = restTemplate;
        this.entity = entity;
        this.random = random;
    }

    public boolean sendMessage(VkMessage vkMessage){
        try
        {
            VkObject vkObject = vkMessage.getObject();
            if(vkObject==null)
                return false;
            if(vkObject.getBody().equals(""))
                return false;
            String botAnswer = vkApiMethod + buildVkApiResponse(vkObject.getUser_id(), vkObject.getBody());
            ResponseEntity<?>  responseEntity = restTemplate.exchange(botAnswer,HttpMethod.GET, entity,  String.class);
            System.out.println(responseEntity.getBody());

        } catch (Exception e) {
           log.error(Instant.now().toString() + " --- " + e);
           return false;
        }
        return true;
    }

    String buildVkApiResponse(int userId, String message){
        return (vkApiMethod + "&user_id=" + userId + "&message=" + "Вы написали: " + message + "&random_id=" + random.nextInt());
    }
}
