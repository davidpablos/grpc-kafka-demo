package com.dpablos.grpckafkademo.mail.application.configuration.mail;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Data
@Accessors(chain = true)
@Validated
public class MailConfiguration {

	@NotEmpty(message = "From property cannot be empty")
	private String from;

	@NotEmpty(message = "Subject property cannot be empty")
	private String subject;

	@NotEmpty(message = "Text property cannot be empty")
	private String text;

}
