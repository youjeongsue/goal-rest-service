package com.goal.restservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  private static final String[] EXCLUDE_PATHS = {"/api/users", "/api/auth/login", "/error/**",
      "/api/test/**", "/api/categories/**"};

  private JwtInterceptor jwtInterceptor;

  public WebConfig(JwtInterceptor jwtInterceptor) {
    this.jwtInterceptor = jwtInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry
        .addInterceptor(jwtInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(EXCLUDE_PATHS);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
        .allowCredentials(false)
        .maxAge(3600);
  }

}
