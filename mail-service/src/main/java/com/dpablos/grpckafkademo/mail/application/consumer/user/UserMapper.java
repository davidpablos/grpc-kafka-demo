package com.dpablos.grpckafkademo.mail.application.consumer.user;

import com.dpablos.grpckafkademo.mail.contract.Mapper;

public class UserMapper implements Mapper<User, com.dpablos.grpckafkademo.mail.domain.model.User> {

	@Override
	public com.dpablos.grpckafkademo.mail.domain.model.User map(User user) {
		return new com.dpablos.grpckafkademo.mail.domain.model.User(user.getId(), user.getEmail());
	}

}
