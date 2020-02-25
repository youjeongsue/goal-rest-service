package com.goal.restservice.web.rest;

import com.goal.restservice.dto.UserDTO;
import com.goal.restservice.service.FollowServiceImpl;
import com.goal.restservice.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The convention of designing API is from...
 *  https://docs.microsoft.com/en-us/sharepoint/dev/general-development/following-people-and-content-rest-api-reference-for-sharepoint#myfollowed
 *
 *
 */
@RestController
@RequestMapping("/api/social.following")
public class FollowController {

    private JwtService jwtService;
    private FollowServiceImpl followServiceImpl;

    FollowController(JwtService jwtService, FollowServiceImpl followServiceImpl){
        this.jwtService = jwtService;
        this.followServiceImpl = followServiceImpl;

    }

    // get users who are following the current users.
    @GetMapping("/my/follower")
    private String myFollowUsers(){
        return "my followers";
    }

    /**
     * get users that the current user is following.
     *
     * @return
     */
    @GetMapping("/my/followed")
    private ResponseEntity<List<UserDTO>> myFollowedUsers(){
        Long userId = jwtService.getUserId();

        List<UserDTO> myFollowedList = followServiceImpl.getAllFollowed(userId);

        return new ResponseEntity<>(myFollowedList, HttpStatus.OK);
    }


    /**
     *
     * @param userDTO : the target user to be followed by the user of userId
     * @return
     */
    @PostMapping("/follow")
    private ResponseEntity<String> followUser(@RequestBody UserDTO userDTO) {
        Long userId = jwtService.getUserId();

        followServiceImpl.userIdFollowUserDTO(userId, userDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    private String unfollowUser(@RequestBody @Valid UserDTO userDTO) {

        return "hello world";
    }


}
