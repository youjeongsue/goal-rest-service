package com.goal.restservice.web.rest;

import com.goal.restservice.common.error.EmailAlreadyUsedException;
import com.goal.restservice.common.error.UserNameAlreadyUsedException;
import com.goal.restservice.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The convention of designing API is from...
 *  https://docs.microsoft.com/en-us/sharepoint/dev/general-development/following-people-and-content-rest-api-reference-for-sharepoint#myfollowed
 *
 *
 */
@RestController
@RequestMapping("/api/social.following")
public class FollowController {

    // get users who are following the current users.
    @GetMapping("/my/follower")
    private String myFollowUsers(){
        return "my followers";
    }

    // get users that the current user is following.
    @GetMapping("/my/followed")
    private String myFollowedUsers(){
        return "my followers";
    }


    // the current user requests for the following to the other user.
    @PostMapping("/follow")
    private String followUser(@RequestBody UserDTO userDTO) {

        return userDTO.getUserName();
    }

    @PutMapping
    private String unfollowUser(@RequestBody @Valid UserDTO userDTO) {

        return "hello world";
    }


}