package com.dpablos.grpckafkademo.user.application.configuration.handler;

import io.grpc.Status;
import io.grpc.StatusException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import org.springframework.transaction.TransactionException;

@GrpcAdvice
@Slf4j
public class GrpcExceptionHandler {

	@net.devh.boot.grpc.server.advice.GrpcExceptionHandler(Exception.class)
	public StatusException handleException(Exception ex) {
		Status status = Status.INTERNAL.withDescription(ex.getLocalizedMessage()).withCause(ex);
		log.error("(GrpcExceptionHandler) Exception: ", ex);
		return status.asException();
	}

	@net.devh.boot.grpc.server.advice.GrpcExceptionHandler(TransactionException.class)
	public StatusException handleTransactionException(TransactionException ex) {
		Status status = Status.INTERNAL.withDescription(ex.getLocalizedMessage()).withCause(ex);
		log.error("(GrpcExceptionHandler) TransactionException: ", ex);
		return status.asException();
	}

}
