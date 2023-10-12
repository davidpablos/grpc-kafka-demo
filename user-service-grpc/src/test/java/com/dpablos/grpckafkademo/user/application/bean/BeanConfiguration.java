package com.dpablos.grpckafkademo.user.application.bean;

import com.dpablos.grpckafkademo.user.utils.TestDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BeanConfiguration {

	@Bean
	public TestDatabase testJdbcDatabase(JdbcTemplate jdbcTemplate) {
		return new TestDatabase(jdbcTemplate);
	}

}
