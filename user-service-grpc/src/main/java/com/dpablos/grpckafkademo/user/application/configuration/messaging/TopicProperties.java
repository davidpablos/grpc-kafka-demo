package com.dpablos.grpckafkademo.user.application.configuration.messaging;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@Validated
public class TopicProperties {

	@NotEmpty(message = "Toppic property cannot be empty")
	private String topic;

	@NotNull(message = "Partitions property cannot be empty")
	private Integer partitions;

	@NotNull(message = "Replicas property cannot be empty")
	private Integer replicas;

}
