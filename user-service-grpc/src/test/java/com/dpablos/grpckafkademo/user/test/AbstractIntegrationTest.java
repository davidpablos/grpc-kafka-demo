package com.dpablos.grpckafkademo.user.test;

import com.dpablos.grpckafkademo.user.application.bean.BeanConfiguration;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(properties = {
	"grpc.server.inProcessName=test",
	"grpc.server.port=-1",
	"grpc.client.inProcess.address=in-process:test"
})
@ContextConfiguration(
	classes = {BeanConfiguration.class}
)
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

	@Container
	static KafkaContainer kafkaContainer =
		new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

	@Container
	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
		.withInitScript("postgresql/initialize.sql");

	@DynamicPropertySource
	static void registryProperties(DynamicPropertyRegistry registry) {
		Startables.deepStart(kafkaContainer, postgreSQLContainer).join();
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

		registry.add("spring.kafka.bootstrap.servers", kafkaContainer::getBootstrapServers);
		registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
	}
}