package com.dpablos.grpckafkademo.user.application.service;

import com.dpablos.grpckafkademo.user.model.UserProto;
import com.dpablos.grpckafkademo.user.model.UsersServiceGrpc;
import com.dpablos.grpckafkademo.user.test.AbstractIntegrationTest;
import com.dpablos.grpckafkademo.user.utils.TestDatabase;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserServiceIT extends AbstractIntegrationTest {

	private static final String SCHEMA = "users";

	@GrpcClient("inProcess")
	UsersServiceGrpc.UsersServiceBlockingStub service;

	@Autowired
	private TestDatabase testDatabase;

	@AfterAll
	void cleanDB() {
		this.testDatabase.executeQuery(String.format("DROP SCHEMA IF EXISTS %s CASCADE", SCHEMA));
	}

	@BeforeEach
	@AfterEach
	void setUp() {
		this.testDatabase.executeQuery("DELETE FROM %s.users", SCHEMA);
	}

	@Test
	void when_user_is_added_then_created_user_is_returned() {
		UserProto.User user = UserProto.User.newBuilder()
			.setEmail("test@email.com")
			.build();

		user = this.service.addUser(user);

		assertNotNull(user);
		assertEquals(1, user.getId());
		assertEquals("test@email.com", user.getEmail());
	}

}