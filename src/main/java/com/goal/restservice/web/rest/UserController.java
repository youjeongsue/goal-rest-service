package com.goal.restservice.web.rest;

import com.goal.restservice.domain.User;
import com.goal.restservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/test/{name}")
  private User saveUser(@PathVariable String name) {
    User user = User.builder().name(name).build();
    return userService.save(user);
  }
}
