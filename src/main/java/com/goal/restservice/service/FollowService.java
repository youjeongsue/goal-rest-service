package com.goal.restservice.service;

import com.goal.restservice.dto.UserDTO;

public interface FollowService {

    void addFollowerToUserFromId(Long userId, UserDTO userDTO);

}
