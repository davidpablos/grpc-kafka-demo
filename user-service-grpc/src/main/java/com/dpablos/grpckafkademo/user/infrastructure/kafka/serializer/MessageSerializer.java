package com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer;

import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.exception.CannotSerializeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageSerializer<T> {

	private final ObjectMapper objectMapper;

	public String serialize(T object) throws CannotSerializeException {
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch(JsonProcessingException e) {
			throw new CannotSerializeException("Cannot serialize object", e);
		}
	}
}
