package com.alibou.chat.controller;

import com.alibou.chat.chat.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatController {

    @MessageMapping("/chat.sendMessage")    // 어떤 URL이 이 메서드를 invoke할지 정하는 메서드
    @SendTo("/topic/public") // 어떤 topic 또는 queue에 보낼까하는 것
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        // HTTP Request는 RequestBody이듯이 WebSocket에서는 @Payload

        return chatMessage;

    }

    @MessageMapping("/chat.addUser")    // 어떤 URL이 이 메서드를 invoke할지 정하는 메서드
    @SendTo("/topic/public") // 어떤 topic 또는 queue에 보낼까하는 것
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        // WebSocket Session에 username추가
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }


}
