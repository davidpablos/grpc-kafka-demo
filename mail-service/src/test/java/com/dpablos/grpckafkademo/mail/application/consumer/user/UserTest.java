package com.dpablos.grpckafkademo.mail.application.consumer.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

	@Test
	void when_user_is_created_then_data_can_be_retrieved() {
		User user = new User(3L, "::email::");

		assertEquals(3L, user.getId());
		assertEquals("::email::", user.getEmail());
	}

}