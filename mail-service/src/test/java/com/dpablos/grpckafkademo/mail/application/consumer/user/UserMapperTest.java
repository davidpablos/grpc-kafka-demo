package com.dpablos.grpckafkademo.mail.application.consumer.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

	@InjectMocks
	private UserMapper mapper;

	@Test
	void when_domain_user_is_mapped_then_it_is_returned() {
		com.dpablos.grpckafkademo.mail.domain.model.User user = this.mapper.map(new User(34L, "::email::"));

		assertEquals(34L, user.getId());
		assertEquals("::email::", user.getEmail());
	}

}