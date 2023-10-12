package com.dpablos.grpckafkademo.user.domain.repository;

import com.dpablos.grpckafkademo.user.domain.model.User;

public interface UserRepository {

	User add(String email);

}
