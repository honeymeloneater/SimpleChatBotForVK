package com.example.SimpleVkBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VkMessage {

    private String type;
    private VkObject object;
    private int group_id;
    private String event_id;
    private String secret;


}