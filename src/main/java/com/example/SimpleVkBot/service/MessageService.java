package com.example.SimpleVkBot.service;


import com.example.SimpleVkBot.model.VkMessage;
import com.example.SimpleVkBot.utils.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
public class MessageService {

    private  final String token = Constants.VkAPI;
    private  final String vkApiMethod = "https://api.vk.com/method/messages.send?access_token=" + token
            + "&v=5.50";
    HttpClient client;
    HttpGet httpGet;

    private final Random random = new Random();

    public void sendMessage(VkMessage vkMessage){
        if(vkMessage.getObject().getBody().equals(""))
            return;
        String botAnswer = vkApiMethod + buildVkApiResponse(vkMessage.getObject().getUser_id(), vkMessage.getObject().getBody());

        try
        {
            client = HttpClientBuilder.create().build();
            httpGet = new HttpGet(botAnswer);
            httpGet.addHeader("accept", "application/x-www-form-urlencoded");

            HttpResponse response = client.execute(httpGet);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Response from server. \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildVkApiResponse(int userId, String message){
        return (vkApiMethod + "&user_id=" + userId + "&message=" + URLEncoder.encode("Вы написали: " + message, StandardCharsets.UTF_8) + "&random_id=" + random.nextInt());
    }
}
