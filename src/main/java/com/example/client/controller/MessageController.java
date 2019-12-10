package com.example.client.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
 
public class MessageController {

	  private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

	  private static StringRedisTemplate stringRedisTemplate;
	  
		  @Autowired
	    public MessageController(  StringRedisTemplate stringRedisTemplate) {
 
	        this.stringRedisTemplate = stringRedisTemplate;
	    }
	    
	    
	    
	  @MessageMapping("/message")
	  @SendTo("/topic/greetings")
	    public void sendWsChatMessage(String message) throws JsonProcessingException {
	        LOGGER.info("Incoming WebSocket Message : {}", message);

	 
	        stringRedisTemplate.convertAndSend("chat", "ghoul");
	    }
}
