package io.github.endron.dataPumpDemo;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringApplicationConfiguration
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {

    public static void main(final String... args) {
    }
}
