package com.goal.restservice.develop;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class H2ServerConfiguration {

  @Profile("local")
  @Bean(name = "datsSource")
  @Primary
  @ConfigurationProperties("spring.datasource.hikari")
  public DataSource dataSource() throws SQLException {
    Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
    return new com.zaxxer.hikari.HikariDataSource();
  }
}