package com.goal.restservice.service;

import com.goal.restservice.domain.Follow;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.FollowRepository;
import com.goal.restservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FollowServiceImpl implements  FollowService{

    private UserRepository userRepository;
    private FollowRepository followRepository;

    FollowServiceImpl(UserRepository userRepository, FollowRepository followRepository){
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    @Override
    public void addFollowerToUserFromId(Long userId, UserDTO userDTO) {
        Optional<User> slaveUser = userRepository.findById(userId);
        Optional<User> masterUser = userRepository.findOneByUserNameIgnoreCase(userDTO.getUserName());

        if (slaveUser.isPresent() && masterUser.isPresent()) {
            //TODO : 이미 팔로우되어있으면
            Follow newFollow = Follow.builder().master(masterUser.get()).slave(slaveUser.get()).build();
            followRepository.save(newFollow);

            masterUser.get().getMasters().add(newFollow);
            slaveUser.get().getSlaves().add(newFollow);
        }

    }
}
