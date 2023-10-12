package com.dpablos.grpckafkademo.user.infrastructure.jpa;

import com.dpablos.grpckafkademo.user.contract.Mapper;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;
import com.dpablos.grpckafkademo.user.infrastructure.repository.SpringUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

	private final SpringUserRepository springUserRepository;
	private final Mapper<UserEntity, User> userMapper;

	@Override
	public User add(String email) {
		return this.userMapper.map(this.springUserRepository.save(new UserEntity(email)));
	}

}
