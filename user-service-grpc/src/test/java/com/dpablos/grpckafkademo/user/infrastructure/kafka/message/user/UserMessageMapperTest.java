package com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMessageMapperTest {

	@InjectMocks
	private UserMessageMapper mapper;

	@Test
	void when_messaging_user_is_mapped_then_user_is_returned() {
		UserMessage userMessage = this.mapper.map(new com.dpablos.grpckafkademo.user.domain.model.User(23L, "::email::"));

		assertEquals(23L, userMessage.getId());
		assertEquals("::email::", userMessage.getEmail());
	}

}