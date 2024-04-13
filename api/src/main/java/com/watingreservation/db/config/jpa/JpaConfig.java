package com.watingreservation.db.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.waitingreservation.db")
@EnableJpaRepositories(basePackages = "com.waitingreservation.db")
public class JpaConfig {
}
