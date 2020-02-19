package com.goal.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication(
    exclude = {SecurityAutoConfiguration.class}) // TODO : Spring Security - disable
public class RestserviceApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestserviceApplication.class, args);
  }
}
