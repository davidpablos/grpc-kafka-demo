package com.dpablos.grpckafkademo.user.infrastructure.jpa;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.model.UserBuilder;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntityBuilder;
import com.dpablos.grpckafkademo.user.infrastructure.repository.SpringUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaUserRepositoryTest {

	@InjectMocks
	private JpaUserRepository repository;

	@Mock
	private SpringUserRepository springUserRepository;

	@Mock
	private Mapper<UserEntity, User> userMapper;


	@Test
	void when_user_is_added_then_user_entity_is_saved() {
		this.repository.add("::email::");

		verify(this.springUserRepository).save(new UserEntity("::email::"));
	}

	@Test
	void when_user_entity_is_saved_then_user_is_mapped() {
		when(this.springUserRepository.save(any())).thenReturn(new UserEntityBuilder().build(4));

		this.repository.add("::email::");

		verify(this.userMapper).map(new UserEntityBuilder().build(4));
	}

	@Test
	void when_user_is_mapped_then_it_is_returned() {
		when(this.userMapper.map(any())).thenReturn(new UserBuilder().build(6));

		User user = this.repository.add("::email::");

		assertEquals(new UserBuilder().build(6), user);
	}

}