package com.itwill.chat_practice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.itwill.chat_practice.domain.WebSocketChatMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebSocketChatController {
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage chatMessage) {
		log.info("sendMessage(chatMessage={})", chatMessage);
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public WebSocketChatMessage addUser(@Payload WebSocketChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		
		log.info("addUser(chatMessage={}, headerAccessor={})", chatMessage, headerAccessor);
		
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		
		return chatMessage;
	}
	
	

}
