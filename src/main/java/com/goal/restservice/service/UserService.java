package com.goal.restservice.service;

import com.goal.restservice.domain.User;


public interface UserService {
    User createUser(User userDTO);

    User readUserById(Long id);
    void updateUser(User user);

    User signIn(String email, String rawPassword) throws Exception;
    boolean isEmailAlreadyUsed(String email);

    User getUserByEmail(String email);
}
