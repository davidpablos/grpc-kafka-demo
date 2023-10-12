package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;
import com.dpablos.grpckafkademo.user.infrastructure.jpa.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mappers {

	@Bean
	public Mapper<UserEntity, User> userMapper() {
		return new UserMapper();
	}

}
