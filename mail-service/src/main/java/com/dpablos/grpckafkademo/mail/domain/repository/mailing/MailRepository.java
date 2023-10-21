package com.dpablos.grpckafkademo.mail.domain.repository.mailing;

import com.dpablos.grpckafkademo.mail.domain.exception.CannotSendMailException;
import com.dpablos.grpckafkademo.mail.domain.model.User;

public interface MailRepository {

	void send(User user) throws CannotSendMailException;

}
