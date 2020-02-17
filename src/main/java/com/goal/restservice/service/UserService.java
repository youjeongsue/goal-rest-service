package com.goal.restservice.service;

import com.goal.restservice.domain.User;
import com.goal.restservice.dto.UserDTO;

public interface UserService {
  UserDTO createUser(UserDTO userDTO);

  UserDTO getUserById(Long id);

  UserDTO getUserByUserName(String userName);

  void updateUser(User user);

  User signIn(String email, String rawPassword) throws Exception;

  boolean isEmailAlreadyUsed(String email);

  boolean isUserNameAlreadyUsed(String userName);
}
