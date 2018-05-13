package com.acme.moneytransfer.delivery.rest.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.acme")
@EnableJpaRepositories("com.acme.moneytransfer.infrastructure")
@EntityScan("com.acme.moneytransfer.domain.model")
public class Config {
}
