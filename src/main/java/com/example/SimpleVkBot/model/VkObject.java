package com.example.SimpleVkBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VkObject {
    private int id;
    private int date;
    private int out;
    private int user_id;
    private int read_state;
    private String title;
    private String body;
    private List<Integer> owner_ids;
}
