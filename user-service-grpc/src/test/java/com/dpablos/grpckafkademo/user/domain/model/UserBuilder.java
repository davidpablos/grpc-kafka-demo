package com.dpablos.grpckafkademo.user.domain.model;

import com.dpablos.grpckafkademo.user.builder.Builder;

public class UserBuilder extends Builder<User> {

	@Override
	public User build(int index) {
		return new User(index, "::email-" + index + "::");
	}

}