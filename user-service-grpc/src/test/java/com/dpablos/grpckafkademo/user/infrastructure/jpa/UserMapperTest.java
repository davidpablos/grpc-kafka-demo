package com.dpablos.grpckafkademo.user.infrastructure.jpa;

import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;
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
	void when_user_is_mapped_then_it_is_returned() {
		User user = this.mapper.map(new UserEntity("::email::").setId(3L));

		assertEquals(3L, user.getId());
		assertEquals("::email::", user.getEmail());
	}

}