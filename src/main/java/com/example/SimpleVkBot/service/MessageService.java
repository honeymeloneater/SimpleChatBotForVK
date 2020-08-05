package com.example.SimpleVkBot.service;


import com.example.SimpleVkBot.model.VkMessage;
import lombok.extern.slf4j.Slf4j;
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


    public void sendMessage(VkMessage vkMessage){
        try
        {
            if(vkMessage.getObject().getBody().equals(""))
                return;
            String botAnswer = vkApiMethod + buildVkApiResponse(vkMessage.getObject().getUser_id(), vkMessage.getObject().getBody());

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<?>  responseEntity = restTemplate.exchange(botAnswer,HttpMethod.GET, entity,  String.class);
            System.out.println(responseEntity.getBody());

        } catch (Exception e) {
           log.error(Instant.now().toString() + " --- " + e);
        }
    }

    private String buildVkApiResponse(int userId, String message){
        return (vkApiMethod + "&user_id=" + userId + "&message=" + "Вы написали: " + message + "&random_id=" + new Random().nextInt());
    }
}
