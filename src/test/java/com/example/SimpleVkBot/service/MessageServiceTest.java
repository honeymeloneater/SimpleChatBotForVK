package com.example.SimpleVkBot.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void checkMessageService() throws Exception{
        assertThat(messageService).isNotNull();
    }
    @Test
    public void checkNullVkMessage() throws Exception{
        messageService.sendMessage(null);
    }

}
