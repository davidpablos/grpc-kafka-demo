package com.dpablos.grpckafkademo.user.infrastructure.jpa;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;

public class UserMapper implements Mapper<UserEntity, User> {

	@Override
	public User map(UserEntity entity) {
		return new User(entity.getId(), entity.getEmail());
	}

}
