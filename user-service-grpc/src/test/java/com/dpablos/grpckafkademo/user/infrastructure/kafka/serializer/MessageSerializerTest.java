package com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer;

import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.exception.CannotSerializeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageSerializerTest {

	@InjectMocks
	private MessageSerializer<TestObject> serializer;

	@Mock
	private ObjectMapper objectMapper;

	@Test
	void when_serialize_then_value_is_written_as_string() throws CannotSerializeException, JsonProcessingException {
		when(this.objectMapper.writeValueAsString(any())).thenReturn("::serialized::");

		this.serializer.serialize(new TestObject());

		verify(this.objectMapper).writeValueAsString(new TestObject());
	}

	@Test
	void when_serialize_successfully_then_string_is_returned() throws CannotSerializeException, JsonProcessingException {
		when(this.objectMapper.writeValueAsString(any())).thenReturn("::serialized::");

		String serializedObject = this.serializer.serialize(new TestObject());

		assertEquals("::serialized::", serializedObject);
	}

	@Test
	void when_cannot_serialize_object_then_exception_is_thrown() throws JsonProcessingException {
		when(this.objectMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

		assertThrows(
			CannotSerializeException.class,
			() -> this.serializer.serialize(new TestObject())
		);
	}

	@EqualsAndHashCode
	private static class TestObject {
		private int seed;
	}

}