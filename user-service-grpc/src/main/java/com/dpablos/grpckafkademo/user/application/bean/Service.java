package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.application.service.UserService;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Service {

	@Bean
	public UserService userService(UserRepository userRepository) {
		return new UserService(userRepository);
	}

}
