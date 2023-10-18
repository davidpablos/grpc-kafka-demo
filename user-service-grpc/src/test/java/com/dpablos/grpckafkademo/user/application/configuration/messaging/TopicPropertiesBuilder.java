package com.dpablos.grpckafkademo.user.application.configuration.messaging;

import com.dpablos.grpckafkademo.user.builder.Builder;

public class TopicPropertiesBuilder extends Builder<TopicProperties> {

	@Override
	public TopicProperties build(int index) {
		return new TopicProperties()
			.setTopic("::topic-" + index + "::")
			.setPartitions(index)
			.setReplicas(index + 1);
	}

}