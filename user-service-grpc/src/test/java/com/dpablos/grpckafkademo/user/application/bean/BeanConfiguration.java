package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.utils.TestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@TestConfiguration
public class BeanConfiguration {

	@Bean
	public TestDatabase testJdbcDatabase(JdbcTemplate jdbcTemplate) {
		return new TestDatabase(jdbcTemplate);
	}

}
