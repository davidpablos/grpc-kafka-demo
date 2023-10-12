package com.dpablos.grpckafkademo.user.infrastructure.entity;

import com.dpablos.grpckafkademo.user.builder.Builder;

public class UserEntityBuilder extends Builder<UserEntity> {

	@Override
	public UserEntity build(int index) {
		return new UserEntity("::email-" + index + "::").setId((long)index);
	}

}