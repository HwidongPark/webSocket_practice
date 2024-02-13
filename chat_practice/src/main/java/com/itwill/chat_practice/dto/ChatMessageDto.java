package com.itwill.chat_practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageDto {
	
	private String type;
	private String content;
	private String sender;
	private String testField;
	
}
