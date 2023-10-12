package com.dpablos.grpckafkademo.user.application.service;

import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import com.dpablos.grpckafkademo.user.model.UserProto;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository userRepository;

	@Mock
	private StreamObserver<UserProto.User> responseObserver;

	@Test
	void addUser() {
		UserProto.User request = UserProto.User.newBuilder()
			.setEmail("test@example.com")
			.build();

		User user = new User(1L, "::email::");
		when(userRepository.add(any(String.class))).thenReturn(user);

		this.service.addUser(request, this.responseObserver);

		verify(this.responseObserver).onNext(UserProto.User.newBuilder()
			.setId(user.getId())
			.setEmail(user.getEmail())
			.build());

		verify(this.responseObserver).onCompleted();
	}

}