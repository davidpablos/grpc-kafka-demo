package com.dpablos.grpckafkademo.user.infrastructure.kafka.message.user;

import com.dpablos.grpckafkademo.user.contract.Mapper;

public class UserMessageMapper implements Mapper<com.dpablos.grpckafkademo.user.domain.model.User, UserMessage> {

	@Override
	public UserMessage map(com.dpablos.grpckafkademo.user.domain.model.User user) {
		return new UserMessage(user.getId(), user.getEmail());
	}

}
