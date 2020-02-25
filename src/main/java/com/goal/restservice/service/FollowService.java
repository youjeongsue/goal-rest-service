package com.goal.restservice.service;

import com.goal.restservice.dto.UserDTO;

import java.util.List;

public interface FollowService {

    void userIdFollowUserDTO(Long userId, UserDTO userDTO);

    List<UserDTO> getAllFollower(Long userId);

    List<UserDTO> getAllFollowed(Long userId);

    void unfollow(Long userId, UserDTO userDTO);
}
