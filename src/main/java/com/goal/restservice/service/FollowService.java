package com.goal.restservice.service;

import com.goal.restservice.dto.UserDTO;

import java.util.List;

public interface FollowService {

    void addFollowerToUserFromId(Long userId, UserDTO userDTO);

    List<UserDTO> getAllFollower(Long userId);

}
