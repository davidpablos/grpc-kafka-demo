package com.dpablos.grpckafkademo.mail.application.exception;

public class CannotDeserializeException extends RuntimeException {

	public CannotDeserializeException(String message, Throwable cause) {
		super(message, cause);
	}

}
