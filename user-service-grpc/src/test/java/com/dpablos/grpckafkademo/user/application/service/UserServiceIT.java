package com.dpablos.grpckafkademo.user.application.service;

import com.dpablos.grpckafkademo.user.model.UserProto;
import com.dpablos.grpckafkademo.user.model.UsersServiceGrpc;
import com.dpablos.grpckafkademo.user.utils.TestDatabase;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(properties = {
	"grpc.server.inProcessName=test",
	"grpc.server.port=-1",
	"grpc.client.inProcess.address=in-process:test"
})
@DirtiesContext
class UserServiceIT {

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