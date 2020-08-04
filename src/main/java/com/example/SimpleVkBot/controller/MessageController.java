package com.example.SimpleVkBot.controller;


import com.example.SimpleVkBot.model.VkMessage;
import com.example.SimpleVkBot.service.MessageService;
import com.example.SimpleVkBot.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class MessageController {
    MessageService messageService;
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    String doChatBotResponse(@RequestBody String incomingMessage){
        Gson gson = new Gson();
        if(incomingMessage != null)
            if(gson.fromJson(incomingMessage, JsonObject.class).get("type").toString().equals("\"message_new\""))
                messageService.sendMessage(new Gson().fromJson(incomingMessage, VkMessage.class));
            else return Constants.confirmCode;
        return "Ok";
    }
}
