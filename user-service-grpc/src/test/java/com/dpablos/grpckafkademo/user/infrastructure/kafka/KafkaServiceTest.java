package com.dpablos.grpckafkademo.user.infrastructure.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaServiceTest {

	@InjectMocks
	private KafkaService service;

	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;

	@Test
	void when_publish_then_message_is_sent() {
		SettableListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
		future.set(new SendResult<>(null, null));
		when(this.kafkaTemplate.send(anyString(), anyString())).thenReturn(future);

		this.service.publish("::topic::", "::message::");

		verify(this.kafkaTemplate).send("::topic::", "::message::");
	}

}