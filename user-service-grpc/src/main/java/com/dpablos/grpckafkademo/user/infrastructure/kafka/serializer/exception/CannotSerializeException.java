package com.dpablos.grpckafkademo.user.infrastructure.kafka.serializer.exception;

public class CannotSerializeException extends Exception {

	public CannotSerializeException(String message, Throwable cause) {
		super(message, cause);
	}

}
