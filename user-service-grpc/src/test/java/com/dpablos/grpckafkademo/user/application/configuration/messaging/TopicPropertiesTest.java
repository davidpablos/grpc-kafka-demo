package com.dpablos.grpckafkademo.user.application.configuration.messaging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopicPropertiesTest {

	@Test
	void when_properties_are_created_then_data_can_be_retrieved() {
		TopicProperties properties = new TopicProperties()
			.setTopic("::topic::")
			.setPartitions(1)
			.setReplicas(2);

		assertEquals("::topic::", properties.getTopic());
		assertEquals(1, properties.getPartitions());
		assertEquals(2, properties.getReplicas());
	}

}