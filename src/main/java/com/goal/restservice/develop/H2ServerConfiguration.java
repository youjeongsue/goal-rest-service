package com.goal.restservice.develop;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class H2ServerConfiguration {

  @Bean
  public Server h2TcpServer() throws SQLException {
    return Server.createTcpServer()
        .start();
  }
}