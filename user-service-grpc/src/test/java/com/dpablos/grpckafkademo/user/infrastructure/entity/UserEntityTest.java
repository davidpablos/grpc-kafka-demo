package com.dpablos.grpckafkademo.user.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {

	@Test
	void when_user_entity_is_created_then_data_can_be_retrieved() {
		UserEntity entity = new UserEntity("::email::").setId(34L);

		assertEquals(34L, entity.getId());
		assertEquals("::email::", entity.getEmail());
	}

}