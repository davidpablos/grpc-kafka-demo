package com.dpablos.grpckafkademo.user.infrastructure.kafka;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.exception.CannotSendMessageException;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.service.MessageService;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user.UserMessage;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.MessageSerializer;
import com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.exception.CannotSerializeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
public class KafkaService implements MessageService {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final MessageSerializer<UserMessage> messageSerializer;
	private final Mapper<User, UserMessage> userMessageMapper;

	@Override
	public void publish(
		String topicName,
		User user
	) throws CannotSendMessageException {
		String serializedUser = this.serializeUser(user);

		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topicName, serializedUser);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info(
					"Message [{}] delivered with result {}",
					user,
					result.getRecordMetadata()
				);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Something went wrong with the message {} ", user);
				throw new CannotSendMessageException(
					String.format("Something went wrong with the message %s", user),
					ex
				);
			}
		});
	}

	private String serializeUser(User user) {
		String serializedUser;
		try {
			serializedUser = this.messageSerializer.serialize(this.userMessageMapper.map(user));
		} catch(CannotSerializeException e) {
			throw new CannotSendMessageException(String.format("Cannot serialize user %s", user.toString()), e);
		}
		return serializedUser;
	}
}
