package com.dpablos.grpckafkademo.user.infrastructure.kafka;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.exception.CannotSendMessageException;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.model.UserBuilder;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user.UserMessage;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user.UserMessageBuilder;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.MessageSerializer;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.exception.CannotSerializeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KafkaServiceTest {

	@InjectMocks
	private KafkaService service;

	@Mock
	private KafkaTemplate<String, String> kafkaTemplate;

	@Mock
	private MessageSerializer<UserMessage> messageSerializer;

	@Mock
	private Mapper<User, UserMessage> userMessageMapper;

	@Test
	void when_publish_then_user_message_is_mapped() {
		SettableListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
		future.set(new SendResult<>(null, null));
		when(this.kafkaTemplate.send(any(), any())).thenReturn(future);

		this.service.publish("::topic::", new UserBuilder().build(3));

		verify(this.userMessageMapper).map(new UserBuilder().build(3));
	}

	@Test
	void when_user_message_is_mapped_then_it_is_serialized() throws CannotSerializeException {
		SettableListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
		future.set(new SendResult<>(null, null));
		when(this.kafkaTemplate.send(any(), any())).thenReturn(future);
		when(this.userMessageMapper.map(any())).thenReturn(new UserMessageBuilder().build(4));

		this.service.publish("::topic::", new UserBuilder().build(3));

		verify(this.messageSerializer).serialize(new UserMessageBuilder().build(4));
	}

	@Test
	void when_user_message_cannot_be_serialized_then_exception_is_thrown() throws CannotSerializeException {
		when(this.userMessageMapper.map(any())).thenReturn(new UserMessageBuilder().build(4));
		when(this.messageSerializer.serialize(any())).thenThrow(CannotSerializeException.class);

		assertThrows(
			CannotSendMessageException.class,
			() -> this.service.publish("::topic::", new UserBuilder().build(3))
		);
	}

	@Test
	void when_publish_then_message_is_sent() throws CannotSerializeException {
		SettableListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
		future.set(new SendResult<>(null, null));
		when(this.kafkaTemplate.send(any(), any())).thenReturn(future);
		when(this.messageSerializer.serialize(any())).thenReturn("::message::");

		this.service.publish("::topic::", new UserBuilder().build(3));

		verify(this.kafkaTemplate).send("::topic::", "::message::");
	}

	@Test
	void when_message_is_published_successfully_then_on_success_is_excecuted() {
		@SuppressWarnings("unchecked")
		ListenableFuture<SendResult<String, String>> future = mock(ListenableFuture.class);

		@SuppressWarnings("unchecked")
		SendResult<String, String> sendResult = mock(SendResult.class);

		when(this.kafkaTemplate.send(any(), any())).thenReturn(future);
		doAnswer(invocationMock -> {
			ListenableFutureCallback<SendResult<String, String>> listenableFutureCallback = invocationMock.getArgument(0);
			listenableFutureCallback.onSuccess(sendResult);
			verify(sendResult).getRecordMetadata();
			return null;
		}).when(future).addCallback(any(ListenableFutureCallback.class));

		this.service.publish("::topic::", new UserBuilder().build(3));
	}

	@Test
	void when_message_is_published_with_error_then_on_failure_is_executed() {
		@SuppressWarnings("unchecked")
		ListenableFuture<SendResult<String, String>> future = mock(ListenableFuture.class);
		Throwable throwable = mock(Throwable.class);
		when(this.kafkaTemplate.send(any(), any())).thenReturn(future);
		doAnswer(invocationMock -> {
			ListenableFutureCallback<SendResult<String, String>> listenableFutureCallback = invocationMock.getArgument(0);
			listenableFutureCallback.onFailure(throwable);
			return null;
		}).when(future).addCallback(any(ListenableFutureCallback.class));

		assertThrows(
			CannotSendMessageException.class,
			() -> this.service.publish("::topic::", new UserBuilder().build(3))
		);
	}

}