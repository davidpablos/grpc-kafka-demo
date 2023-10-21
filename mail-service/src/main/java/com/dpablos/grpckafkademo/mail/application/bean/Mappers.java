package com.dpablos.grpckafkademo.mail.application.bean;

import com.dpablos.grpckafkademo.mail.application.consumer.user.User;
import com.dpablos.grpckafkademo.mail.application.consumer.user.UserMapper;
import com.dpablos.grpckafkademo.mail.contract.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mappers {

	@Bean
	public Mapper<User, com.dpablos.grpckafkademo.mail.domain.model.User> userMapper() {
		return new UserMapper();
	}

}
