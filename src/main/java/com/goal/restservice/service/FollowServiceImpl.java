package com.goal.restservice.service;

import com.goal.restservice.domain.Follow;
import com.goal.restservice.domain.User;
import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.repository.FollowRepository;
import com.goal.restservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * Make a relation between the follower(slave) and the followed(master)
     *
     * @param userId : the user id who want to follow someone (slave)
     * @param userDTO : the userDTO which only has a username and is the followed.
     */
    @Override
    public void userIdFollowUserDTO(Long userId, UserDTO userDTO) {
        Optional<User> slaveUser = userRepository.findById(userId);
        Optional<User> masterUser = userRepository.findOneByUserNameIgnoreCase(userDTO.getUserName());

        if (slaveUser.isPresent() && masterUser.isPresent()) {
            //TODO : 이미 팔로우되어있으면
            Follow newFollow = Follow.builder().master(masterUser.get()).slave(slaveUser.get()).build();
            followRepository.save(newFollow);
        }
    }

    /**
     * Retrieve all the follower of th specific user.
     *
     * @param userId : user who want to know all the followers.
     * @return
     */
    @Override
    public List<UserDTO> getAllFollower(Long userId) {
        List<Follow> followList = followRepository.findByMasterId(userId);
        ArrayList<UserDTO> followerUserDTOList = new ArrayList<>();

        for(Follow follow : followList){
            followerUserDTOList.add(UserDTO.ByFollowerBuilder().user(follow.getSlave()).build());
        }

        return followerUserDTOList;
    }

    /**
     * Retrieve all the users who are followed by the specific user
     *
     * @param userId : the user who want to know all the user who he is following to.
     * @return
     */
    @Override
    public List<UserDTO> getAllFollowed(Long userId) {
        List<Follow> followedList = followRepository.findBySlaveId(userId);
        ArrayList<UserDTO> followedUserDTOList = new ArrayList<>();

        for(Follow follow : followedList){
            followedUserDTOList.add(UserDTO.ByFollowerBuilder().user(follow.getMaster()).build());
        }

        return followedUserDTOList;
    }

    /**
     * The user identified by userId will cancel the follow to the userDTO.
     *
     * @param userId : user who want to remove the follow.
     * @param userDTO : user who is not interesting any more.
     */
    @Override
    @Transactional
    public void unfollow(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findOneByUserNameIgnoreCase(userDTO.getUserName());
        User oneThatNotAttractive =optionalUser.orElse(null);

        followRepository.deleteFollowing(userId, oneThatNotAttractive.getId());
    }
}
