package com.example.SimpleVkBot.controller;


import com.example.SimpleVkBot.model.VkMessage;
import com.example.SimpleVkBot.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MessageController {

    private final MessageService messageService;
    private final Gson gson;

    @Value("${vk.api.confirm.code}")
    private  String confirmCode;

    public MessageController(MessageService messageService, Gson gson) {
        this.messageService = messageService;
        this.gson = gson;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String doChatBotResponse(@RequestBody(required=false)  String incomingMessage){
        if(incomingMessage != null)
            if(gson.fromJson(incomingMessage, JsonObject.class).get("type").toString().equals("\"message_new\""))
                messageService.sendMessage(gson.fromJson(incomingMessage, VkMessage.class));
            else return confirmCode;
        return "Ok";
    }
}
