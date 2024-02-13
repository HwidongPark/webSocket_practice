package com.itwill.chat_practice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WebSocketChatMessage {
	
	private String type;
	private String content;
	private String sender;
	
}
