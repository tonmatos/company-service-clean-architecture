package br.com.wnt.domain.common


import groovy.transform.CompileDynamic
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [Initializer.class])
@CompileDynamic
abstract class BaseRepositoryTest extends Specification {
    public static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres")
                    .withDatabaseName("postgres")
                    .withUsername("postgres")
                    .withPassword("docker")

    static {
        postgres.start()
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.datasource.username=" + postgres.getUsername(),
                    "spring.datasource.password=" + postgres.getPassword(),
            )
                    .applyTo(configurableApplicationContext.getEnvironment())
        }
    }

}
