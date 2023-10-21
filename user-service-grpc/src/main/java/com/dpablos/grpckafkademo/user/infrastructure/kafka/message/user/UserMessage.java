package com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserMessage {

	@JsonProperty("id")
	private final long id;

	@JsonProperty("email")
	private final String email;

}
