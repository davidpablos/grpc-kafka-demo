package com.dpablos.grpckafkademo.user.application.configuration.interceptor;

import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import io.grpc.ServerMethodDefinition;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GrpcLogInterceptorTest {

	@Mock
	private MethodDescriptor.Marshaller<String> requestMarshaller;

	@Mock
	private MethodDescriptor.Marshaller<Integer> responseMarshaller;

	@Mock
	private ServerCallHandler<String, Integer> handler;

	@Mock
	private ServerCall.Listener<String> listener;

	private MethodDescriptor<String, Integer> flowMethod;

	private ServerCall<String, Integer> call = this.initServerCall("basic/flow");

	private ServerServiceDefinition serviceDefinition;

	private final Metadata headers = new Metadata();

	@BeforeEach
	void setUp() {
		this.flowMethod = MethodDescriptor.<String, Integer>newBuilder()
			.setType(MethodDescriptor.MethodType.UNKNOWN)
			.setFullMethodName("basic/flow")
			.setRequestMarshaller(this.requestMarshaller)
			.setResponseMarshaller(this.responseMarshaller)
			.build();

		when(this.handler.startCall(any(), any())).thenReturn(this.listener);

		this.serviceDefinition = ServerServiceDefinition.builder(new ServiceDescriptor("basic", this.flowMethod))
			.addMethod(this.flowMethod, this.handler)
			.build();
	}

	@Test
	void when_intercept_with_one_flow_then_correct_handler_is_called() {
		ServerServiceDefinition intercepted = ServerInterceptors.intercept(
			this.serviceDefinition,
			Collections.<ServerInterceptor>singletonList(new GrpcLogInterceptor())
		);
		getMethod(intercepted, "basic/flow").getServerCallHandler().startCall(this.call, this.headers);
		verify(this.handler).startCall(this.call, this.headers);
		verifyNoMoreInteractions(this.handler);
	}

	@Test
	void when_intercept_with_multiple_flows_then_correct_handler_is_called() {
		@SuppressWarnings("unchecked")
		ServerCallHandler<String, Integer> handler2 = mock(ServerCallHandler.class);
		MethodDescriptor<String, Integer> flowMethod2 = this.flowMethod.toBuilder()
			.setFullMethodName("basic/flow2").build();

		this.serviceDefinition = ServerServiceDefinition.builder(new ServiceDescriptor(
				"basic",
				this.flowMethod,
				flowMethod2
			))
			.addMethod(this.flowMethod, this.handler)
			.addMethod(flowMethod2, handler2).build();

		ServerServiceDefinition intercepted = ServerInterceptors.intercept(
			this.serviceDefinition,
			Collections.<ServerInterceptor>singletonList(new GrpcLogInterceptor())
		);
		getMethod(intercepted, "basic/flow").getServerCallHandler().startCall(this.call, this.headers);
		verify(this.handler).startCall(this.call, this.headers);
		verifyNoMoreInteractions(this.handler);
		verifyNoMoreInteractions(handler2);

		ServerCall<String, Integer> call2 = this.initServerCall("basic/flow2");
		getMethod(intercepted, "basic/flow2").getServerCallHandler().startCall(call2, this.headers);
		verify(handler2).startCall(call2, this.headers);
		verifyNoMoreInteractions(this.handler);
		verifyNoMoreInteractions(handler2);
	}

	@SuppressWarnings("unchecked")
	private static ServerMethodDefinition<String, Integer> getMethod(
		ServerServiceDefinition serviceDef, String name
	) {
		return (ServerMethodDefinition<String, Integer>)serviceDef.getMethod(name);
	}

	private ServerCall<String, Integer> initServerCall(String fullMethodName) {
		return new ServerCall<String, Integer>() {
			@Override
			public void request(int i) {

			}

			@Override
			public void sendHeaders(Metadata metadata) {

			}

			@Override
			public void sendMessage(Integer integer) {

			}

			@Override
			public void close(Status status, Metadata metadata) {

			}

			@Override
			public boolean isCancelled() {
				return false;
			}

			@Override
			public MethodDescriptor<String, Integer> getMethodDescriptor() {
				return MethodDescriptor.<String, Integer>newBuilder()
					.setType(MethodDescriptor.MethodType.UNKNOWN)
					.setFullMethodName(fullMethodName)
					.setRequestMarshaller(new MethodDescriptor.Marshaller<String>() {
						@Override
						public InputStream stream(String value) {
							return null;
						}

						@Override
						public String parse(InputStream stream) {
							return null;
						}
					})
					.setResponseMarshaller(new MethodDescriptor.Marshaller<Integer>() {
						@Override
						public InputStream stream(Integer value) {
							return null;
						}

						@Override
						public Integer parse(InputStream stream) {
							return null;
						}
					})
					.build();
			}
		};
	}

}