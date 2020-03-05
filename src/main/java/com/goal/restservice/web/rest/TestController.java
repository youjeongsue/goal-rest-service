package com.goal.restservice.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

  @GetMapping("/error/1")
  private String defaultErrorResponse() throws IllegalAccessException {

    throw new IllegalAccessException();
  }

  @GetMapping("/error/2")
  private String customeErrorResponse() {

    return "test";
  }
}
