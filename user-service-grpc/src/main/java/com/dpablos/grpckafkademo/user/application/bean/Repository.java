package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;
import com.dpablos.grpckafkademo.user.infrastructure.jpa.JpaUserRepository;
import com.dpablos.grpckafkademo.user.infrastructure.repository.SpringUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Repository {

	@Bean
	public UserRepository userRepository(
		SpringUserRepository springUserRepository,
		Mapper<UserEntity, User> userMapper
	) {
		return new JpaUserRepository(springUserRepository, userMapper);
	}

}
