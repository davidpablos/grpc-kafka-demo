package com.dpablos.grpckafkademo.user.application.service;

import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import com.dpablos.grpckafkademo.user.domain.service.MessageService;
import com.dpablos.grpckafkademo.user.model.UserProto;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	private UserService service;

	@Mock
	private UserRepository userRepository;

	@Mock
	private MessageService messageService;

	@Mock
	private StreamObserver<UserProto.User> responseObserver;

	@BeforeEach
	void setUp() {
		this.service = new UserService(this.userRepository, this.messageService, "::topic::");
	}

	@Test
	void when_add_user_is_executed_then_user_is_added() {
		UserProto.User request = UserProto.User.newBuilder()
			.setEmail("test@example.com")
			.build();

		User user = new User(1L, "::email::");
		when(this.userRepository.add(any(String.class))).thenReturn(user);

		this.service.addUser(request, this.responseObserver);

		verify(this.userRepository).add("::email::");
	}

	@Test
	void when_user_is_added_then_grpc_petition_is_completed() {
		UserProto.User request = UserProto.User.newBuilder()
			.setEmail("test@example.com")
			.build();

		User user = new User(1L, "::email::");
		when(this.userRepository.add(any(String.class))).thenReturn(user);

		this.service.addUser(request, this.responseObserver);

		verify(this.responseObserver).onNext(UserProto.User.newBuilder()
			.setId(user.getId())
			.setEmail(user.getEmail())
			.build());

		verify(this.responseObserver).onCompleted();
	}

}