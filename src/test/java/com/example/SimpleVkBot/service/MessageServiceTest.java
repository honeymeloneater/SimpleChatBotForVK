package com.example.SimpleVkBot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.SimpleVkBot.model.VkMessage;
import com.example.SimpleVkBot.model.VkObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Random;


public class MessageServiceTest {

    @Mock
    private HttpEntity<String> httpEntity;
    @Mock
    private Random random;
    @Mock
    private RestTemplate restTemplate;

    private MessageService messageService ;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        messageService = spy(new MessageService(restTemplate,httpEntity,random));
    }

    @Test
    public void checkNullVkMessage_sendMessage() throws Exception{
        //Given
        VkMessage vkMessage=null;
        //When
        boolean actual = messageService.sendMessage(vkMessage);
        //Then
        assertThat(actual).isFalse();
    }

    @Test
    public void checkNullVkObject_sendMessage()  throws Exception{
        //Given
        VkMessage vkMessage = spy(VkMessage.class);
        VkObject vkObject = null;
        vkMessage.setObject(vkObject);
        //When
        boolean actual = messageService.sendMessage(vkMessage);
        //Then
        verify(vkMessage).getObject();
        assertThat(actual).isFalse();

    }
    @Test
    public void checkEmptyMessage_sendMessage()  throws Exception{
        //Given
        VkMessage vkMessage = spy(VkMessage.class);
        VkObject vkObject = spy(VkObject.class);
        vkObject.setBody("");
        vkMessage.setObject(vkObject);
        //When
        boolean actual = messageService.sendMessage(vkMessage);
        //Then
        verify(vkMessage).getObject();
        verify(vkObject).getBody();
        assertThat(actual).isFalse();

    }
    @Test
    public void checkForTrue_sendMessage() throws Exception{

        //GIVEN
        VkMessage vkMessage = spy(VkMessage.class);
        VkObject vkObject = spy(VkObject.class);
        vkObject.setBody("test string");
        vkMessage.setObject(vkObject);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        //WHEN
        doReturn("something returning value").when(messageService)
                .buildVkApiResponse(4, "123");
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(String.class)))
                .thenReturn(responseEntity);

        boolean actual = messageService.sendMessage(vkMessage);

        //THEN
        assertThat(actual).isTrue();
        //verify(restTemplate).exchange(any(), eq(HttpMethod.GET), any(), eq(String.class));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
