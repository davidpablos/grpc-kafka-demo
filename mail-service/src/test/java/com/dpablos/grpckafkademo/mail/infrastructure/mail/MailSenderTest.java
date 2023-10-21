package com.dpablos.grpckafkademo.mail.infrastructure.mail;

import com.dpablos.grpckafkademo.mail.domain.exception.CannotSendMailException;
import com.dpablos.grpckafkademo.mail.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class MailSenderTest {

	@InjectMocks
	private MailSender sender;

	@Mock
	private JavaMailSender javaMailSender;

	@Test
	void when_sender_is_used_then_mail_is_sent() throws CannotSendMailException {
		this.sender.send(new User(34L, "::email::"));

		verify(this.javaMailSender).send(any(MimeMessagePreparator.class));
		verifyNoMoreInteractions(this.javaMailSender);
	}

	@Test
	void when_mail_is_sent_successfully_then_no_exception_is_thrown() {
		assertDoesNotThrow(() -> this.sender.send(new User(34L, "::email::")));
	}

	@Test
	void when_mail_cannot_be_sent_then_exception_is_thrown() {
		doThrow(RuntimeException.class).when(this.javaMailSender).send(any(MimeMessagePreparator.class));

		assertThrows(
			CannotSendMailException.class,
			() -> this.sender.send(new User(34L, "::email::"))
		);
	}

}