package com.dpablos.grpckafkademo.user.domain.service;

import com.dpablos.grpckafkademo.user.domain.exception.CannotSendMessageException;

public interface MessageService {

	void publish(String topicName, String message) throws CannotSendMessageException;

}
