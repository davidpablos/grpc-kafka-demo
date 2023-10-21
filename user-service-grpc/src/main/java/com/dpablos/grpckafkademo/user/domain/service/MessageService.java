package com.dpablos.grpckafkademo.user.domain.service;

import com.dpablos.grpckafkademo.user.domain.exception.CannotSendMessageException;
import com.dpablos.grpckafkademo.user.domain.model.User;

public interface MessageService {

	void publish(String topicName, User user) throws CannotSendMessageException;

}
