package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.application.configuration.messaging.MessagingProperties;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class Messaging {

	@Bean
	public ProducerFactory<?, ?> kafkaProducerFactory(KafkaProperties properties) {
		Map<String, Object> producerProperties = properties.buildProducerProperties();
		producerProperties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		return new DefaultKafkaProducerFactory<>(producerProperties);
	}

	@Bean
	public NewTopic usersTopic(MessagingProperties properties) {
		return TopicBuilder.name(properties.getUsersTopic().getTopic())
			.partitions(properties.getUsersTopic().getPartitions())
			.replicas(properties.getUsersTopic().getReplicas())
			.build();
	}

}
