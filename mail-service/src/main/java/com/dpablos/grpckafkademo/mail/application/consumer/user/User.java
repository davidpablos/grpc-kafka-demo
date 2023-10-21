package com.dpablos.grpckafkademo.mail.application.consumer.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {

	@JsonProperty("id")
	private final long id;

	@JsonProperty("email")
	private final String email;

}
