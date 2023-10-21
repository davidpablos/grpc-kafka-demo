package com.dpablos.grpckafkademo.mail.domain.model;

import com.dpablos.grpckafkademo.mail.builder.Builder;

public class UserBuilder extends Builder<User> {

	@Override
	public User build(int index) {
		return new User(index, "::email-" + index + "::");
	}

}
