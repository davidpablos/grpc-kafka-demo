package com.dpablos.grpckafkademo.user.domain.exception;

public class CannotSendMessageException extends RuntimeException {

	public CannotSendMessageException(String message, Throwable cause) {
		super(message, cause);
	}

}
