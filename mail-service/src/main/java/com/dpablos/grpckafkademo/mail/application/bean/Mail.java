package com.dpablos.grpckafkademo.mail.application.bean;

import com.dpablos.grpckafkademo.mail.domain.repository.mailing.MailRepository;
import com.dpablos.grpckafkademo.mail.application.configuration.mail.MailConfiguration;
import com.dpablos.grpckafkademo.mail.infrastructure.mail.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class Mail {

	@Bean
	public MailRepository customMailSender(JavaMailSender javaMailSender, MailConfiguration mailConfiguration) {
		return new MailSender(javaMailSender, mailConfiguration);
	}

}
