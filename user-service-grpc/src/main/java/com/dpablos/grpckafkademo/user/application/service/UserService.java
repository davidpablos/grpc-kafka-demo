package com.dpablos.grpckafkademo.user.application.service;

import com.dpablos.grpckafkademo.user.application.configuration.interceptor.GrpcLogInterceptor;
import com.dpablos.grpckafkademo.user.domain.model.User;
import com.dpablos.grpckafkademo.user.domain.repository.UserRepository;
import com.dpablos.grpckafkademo.user.domain.service.MessageService;
import com.dpablos.grpckafkademo.user.model.UserProto;
import com.dpablos.grpckafkademo.user.model.UsersServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService(interceptors = {GrpcLogInterceptor.class})
@RequiredArgsConstructor
public class UserService extends UsersServiceGrpc.UsersServiceImplBase {

	private final UserRepository userRepository;
	private final MessageService messageService;
	private final String usersTopic;

	@Override
	public void addUser(
		UserProto.UserCreateRequest request,
		StreamObserver<UserProto.UserCreateResponse> responseObserver
	) {
		User user = this.userRepository.add(request.getEmail());
		responseObserver.onNext(UserProto.UserCreateResponse.newBuilder()
			.setUser(UserProto.User.newBuilder().setId(user.getId()).setEmail(user.getEmail()).build())
			.build());
		responseObserver.onCompleted();

		this.messageService.publish(this.usersTopic, user);
	}

}
