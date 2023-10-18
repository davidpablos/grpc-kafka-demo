package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.application.configuration.messaging.MessagingProperties;
import com.dpablos.grpckafkademo.user.application.service.UserService;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import com.dpablos.grpckafkademo.user.domain.service.MessageService;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.KafkaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class Service {

	@Bean
	public MessageService messageService(KafkaTemplate<String, String> kafkaTemplate) {
		return new KafkaService(kafkaTemplate);
	}

	@Bean
	public UserService userService(
		UserRepository userRepository,
		MessageService messageService,
		MessagingProperties properties
	) {
		return new UserService(userRepository, messageService, properties.getUsersTopic().getTopic());
	}

}
