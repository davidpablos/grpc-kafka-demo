package com.dpablos.grpckafkademo.user.application.configuration.handler;

import io.grpc.Status;
import io.grpc.StatusException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GrpcExceptionHandlerTest {

	@InjectMocks
	private GrpcExceptionHandler handler;

	@Test
	void when_exception_is_thrown_then_status_exception_is_returned() {
		Exception exception = mock(Exception.class);
		when(exception.getLocalizedMessage()).thenReturn("::error-message::");

		StatusException status = this.handler.handleException(exception);

		assertEquals(Status.INTERNAL.getCode(), status.getStatus().getCode());
		assertEquals("::error-message::", status.getStatus().getDescription());
		assertEquals(exception, status.getStatus().getCause());
	}

	@Test
	void when_transaction_exception_is_thrown_then_status_exception_is_returned() {
		TransactionException exception = mock(TransactionException.class);
		when(exception.getLocalizedMessage()).thenReturn("::error-message::");

		StatusException status = this.handler.handleTransactionException(exception);

		assertEquals(Status.INTERNAL.getCode(), status.getStatus().getCode());
		assertEquals("::error-message::", status.getStatus().getDescription());
		assertEquals(exception, status.getStatus().getCause());
	}

}