package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user.UserMessage;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.MessageSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Serializer {

	@Bean
	public MessageSerializer<UserMessage> messageSerializer(ObjectMapper objectMapper) {
		return new MessageSerializer<>(objectMapper);
	}
}
