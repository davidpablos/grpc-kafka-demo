package com.dpablos.grpckafkademo.mail.application.bean;

import com.dpablos.grpckafkademo.mail.application.consumer.user.User;
import com.dpablos.grpckafkademo.mail.application.consumer.user.UserConsumer;
import com.dpablos.grpckafkademo.mail.contract.Mapper;
import com.dpablos.grpckafkademo.mail.domain.repository.mailing.MailRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Consumer {

	@Bean
	public UserConsumer userConsumer(
		MailRepository mailRepository,
		Mapper<User, com.dpablos.grpckafkademo.mail.domain.model.User> userMapper
	) {
		return new UserConsumer(mailRepository, userMapper);
	}

}
