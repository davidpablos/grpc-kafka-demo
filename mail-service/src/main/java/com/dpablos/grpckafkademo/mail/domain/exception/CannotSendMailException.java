package com.dpablos.grpckafkademo.mail.domain.exception;

public class CannotSendMailException extends Exception {

	public CannotSendMailException(String message, Throwable cause) {
		super(message, cause);
	}

}
