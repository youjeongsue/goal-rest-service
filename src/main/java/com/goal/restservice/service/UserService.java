package com.goal.restservice.service;

import com.goal.restservice.domain.User;
import com.goal.restservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public User findOne(Long id) {
    return userRepository.getOne(id);
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
