package com.dpablos.grpckafkademo.user.infrastructure.repository;

import com.dpablos.grpckafkademo.user.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpringUserRepository extends CrudRepository<UserEntity, Long> {

}
