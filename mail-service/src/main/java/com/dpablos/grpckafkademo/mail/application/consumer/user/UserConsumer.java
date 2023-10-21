package com.dpablos.grpckafkademo.mail.application.consumer.user;

import com.dpablos.grpckafkademo.mail.contract.Mapper;
import com.dpablos.grpckafkademo.mail.domain.exception.CannotSendMailException;
import com.dpablos.grpckafkademo.mail.domain.repository.mailing.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@RequiredArgsConstructor
public class UserConsumer {

	private final MailRepository mailRepository;
	private final Mapper<User, com.dpablos.grpckafkademo.mail.domain.model.User> userMapper;

	@KafkaListener(topics = "${messaging.userCreated.topic}", groupId = "${messaging.userCreated.groupId}", containerFactory = "userKafkaListenerFactory")
	public void consumeUser(User user) {
		log.info("Consumed User Message: {}", user);

		try {
			this.mailRepository.send(this.userMapper.map(user));
		} catch(CannotSendMailException e) {
			log.error("Cannot send email", e);
		}
	}
}
