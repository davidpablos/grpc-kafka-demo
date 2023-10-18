package com.dpablos.grpckafkademo.user.application.configuration.messaging;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "messaging")
@Validated
public class MessagingProperties {

	@NotNull(message = "The usersTopic property cannot be null")
	private TopicProperties usersTopic;

}
