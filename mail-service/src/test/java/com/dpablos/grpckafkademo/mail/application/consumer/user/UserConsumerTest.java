package com.dpablos.grpckafkademo.mail.application.consumer.user;

import com.dpablos.grpckafkademo.mail.contract.Mapper;
import com.dpablos.grpckafkademo.mail.domain.exception.CannotSendMailException;
import com.dpablos.grpckafkademo.mail.domain.repository.mailing.MailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserConsumerTest {

	@InjectMocks
	private UserConsumer consumer;

	@Mock
	private MailRepository mailRepository;

	@Mock
	private Mapper<User, com.dpablos.grpckafkademo.mail.domain.model.User> userMapper;

	@Test
	void when_message_is_consumed_then_user_is_mapped_to_domain() {
		this.consumer.consumeUser(new UserBuilder().build(3));

		verify(this.userMapper).map(new UserBuilder().build(3));
	}

	@Test
	void when_user_is_mapped_to_domain_then_email_is_sent() throws CannotSendMailException {
		when(this.userMapper.map(any())).thenReturn(
			new com.dpablos.grpckafkademo.mail.domain.model.UserBuilder().build(6)
		);

		this.consumer.consumeUser(new UserBuilder().build(3));

		verify(this.mailRepository).send(new com.dpablos.grpckafkademo.mail.domain.model.UserBuilder().build(6));
	}

	@Test
	void when_email_cannot_be_sent_then_exception_is_silenced() throws CannotSendMailException {
		doThrow(CannotSendMailException.class).when(this.mailRepository).send(any());

		assertDoesNotThrow(() -> this.consumer.consumeUser(new UserBuilder().build(3)));
	}

}