package com.dpablos.grpckafkademo.user.application.configuration.messaging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessagingPropertiesTest {

	@Test
	void when_properties_are_created_then_data_can_be_retrieved() {
		MessagingProperties properties = new MessagingProperties()
			.setUsersTopic(new TopicPropertiesBuilder().build(2));

		assertEquals(new TopicPropertiesBuilder().build(2), properties.getUsersTopic());
	}

}