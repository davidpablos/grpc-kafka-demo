package com.dpablos.grpckafkademo.mail.application.configuration.mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailConfigurationTest {

	@Test
	void when_configuration_is_created_then_data_can_be_retrieved() {
		MailConfiguration configuration = new MailConfiguration()
			.setFrom("::from::")
			.setSubject("::subject::")
			.setText("::text::");

		assertEquals("::from::", configuration.getFrom());
		assertEquals("::subject::", configuration.getSubject());
		assertEquals("::text::", configuration.getText());
	}

}