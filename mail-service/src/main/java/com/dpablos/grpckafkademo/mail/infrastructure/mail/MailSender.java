package com.dpablos.grpckafkademo.mail.infrastructure.mail;

import com.dpablos.grpckafkademo.mail.domain.exception.CannotSendMailException;
import com.dpablos.grpckafkademo.mail.domain.model.User;
import com.dpablos.grpckafkademo.mail.domain.repository.mailing.MailRepository;
import com.dpablos.grpckafkademo.mail.application.configuration.mail.MailConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@RequiredArgsConstructor
public class MailSender implements MailRepository {

	private final JavaMailSender javaMailSender;
	private final MailConfiguration mailConfiguration;

	public void send(User user) throws CannotSendMailException {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			message.setFrom(this.mailConfiguration.getFrom());
			message.setTo(user.getEmail());
			message.setSubject(this.mailConfiguration.getSubject());
			message.setText(String.format(this.mailConfiguration.getText(), user.getEmail()), false);
		};

		try {
			this.javaMailSender.send(messagePreparator);
		} catch(RuntimeException e) {
			throw new CannotSendMailException("Cannot set email", e);
		}
	}

}
