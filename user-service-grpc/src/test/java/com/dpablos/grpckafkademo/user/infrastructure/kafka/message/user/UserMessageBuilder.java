package com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user;

import com.dpablos.grpckafkademo.user.builder.Builder;

public class UserMessageBuilder extends Builder<UserMessage> {

	@Override
	public UserMessage build(int index) {
		return new UserMessage(index, "::email-" + index + "::");
	}

}