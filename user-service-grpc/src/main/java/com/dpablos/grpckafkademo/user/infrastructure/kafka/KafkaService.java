package com.dpablos.grpckafkademo.user.infrastructure.kafka;

import com.dpablos.grpckafkademo.user.domain.exception.CannotSendMessageException;
import com.dpablos.grpckafkademo.user.domain.service.MessageService;
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

	@Override
	public void publish(String topicName, String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info(
					"Message [{}] delivered with result {}",
					message,
					result.getRecordMetadata()
				);
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Something went wrong with the message {} ", message);
				throw new CannotSendMessageException(
					String.format("Something went wrong with the message %s", message),
					ex
				);
			}
		});
	}
}
