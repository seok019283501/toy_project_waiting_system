package com.watingreservation.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.watingreservation.db")
@EntityScan(basePackages = "com.watingreservation.db")
public class JpaConfig {
}
