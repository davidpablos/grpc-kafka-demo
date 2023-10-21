package com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user;

import com.dpablos.grpckafkademo.user.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMessageTest {

	@Test
	void when_user_is_created_then_data_can_be_retrieved() {
		User user = new User(3L, "::email::");

		assertEquals(3L, user.getId());
		assertEquals("::email::", user.getEmail());
	}

}