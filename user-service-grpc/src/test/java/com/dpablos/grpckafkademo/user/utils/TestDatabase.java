package com.dpablos.grpckafkademo.user.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Table;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class TestDatabase {

	private final JdbcTemplate jdbcTemplate;

	public void executeQuery(String sqlFormat, Object... parameters) {
		this.jdbcTemplate.execute(String.format(Locale.ENGLISH, sqlFormat, parameters));
	}

	public <T> List<T> select(String operator, Class<T> returnedType, Map<String, Object> where, RowMapper<T> rowMapper) {
		StringBuilder whereBuilder = new StringBuilder();
		where.forEach((key, value) -> {
			whereBuilder.append(" AND ").append(key).append(" = ");
			if(value instanceof Long || value instanceof Boolean || value instanceof Double) {
				whereBuilder.append(value);
			}
			if(value instanceof String || value instanceof UUID) {
				whereBuilder.append("'").append(value).append("'");
			}

		});

		return this.jdbcTemplate.query(String.format(
			"SELECT * FROM %s.%s WHERE %s",
			operator,
			returnedType.getAnnotation(Table.class).name(),
			whereBuilder
		).replaceFirst(" AND ", ""), rowMapper);
	}

}
