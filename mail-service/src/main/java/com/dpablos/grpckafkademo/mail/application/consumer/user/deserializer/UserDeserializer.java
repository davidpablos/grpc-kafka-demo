package com.dpablos.grpckafkademo.mail.application.consumer.user.deserializer;

import com.dpablos.grpckafkademo.mail.application.consumer.user.User;
import com.dpablos.grpckafkademo.mail.application.exception.CannotDeserializeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;

@NoArgsConstructor
public class UserDeserializer implements Deserializer<User> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public User deserialize(String topic, byte[] data) {
		try {
			return this.objectMapper.readValue(data, User.class);
		} catch (Exception e) {
			throw new CannotDeserializeException("Error deserializing User", e);
		}
	}

}