package com.itwill.chat_practice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.itwill.chat_practice.dto.ChatMessageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {
	
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/sub/chatroom")
	public ChatMessageDto sendMessage(@Payload ChatMessageDto messageDto) {
		
		log.info(messageDto.toString());
		
		
		return messageDto;		
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/sub/chatroom")
	public ChatMessageDto addUser(@Payload ChatMessageDto messageDto, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", messageDto.getSender());
		
		return messageDto;
	}
	
}
