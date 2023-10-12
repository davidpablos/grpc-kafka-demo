package com.dpablos.grpckafkademo.user.infrastructure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_users")
	@SequenceGenerator(name = "sequence_users", allocationSize = 1)
	private Long id;

	@Column(name = "email", nullable = false)
	private final String email;

}
