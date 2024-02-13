package com.alibou.chat.chat;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;

}
