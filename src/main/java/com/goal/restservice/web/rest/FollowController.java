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
 * https://docs.microsoft.com/en-us/sharepoint/dev/general-development/following-people-and-content-rest-api-reference-for-sharepoint#myfollowed
 */
@RestController
@RequestMapping("/api/social.following")
public class FollowController {

  private JwtService jwtService;
  private FollowServiceImpl followServiceImpl;

  FollowController(JwtService jwtService, FollowServiceImpl followServiceImpl) {
    this.jwtService = jwtService;
    this.followServiceImpl = followServiceImpl;
  }

  /**
   * get users who are following me.
   *
   * @return
   */
  @GetMapping("/my/follower")
  private ResponseEntity<List<UserDTO>> myFollowUsers() {
    Long userId = jwtService.getUserId();
    List<UserDTO> myFollowedList = followServiceImpl.getAllFollower(userId);

    return new ResponseEntity<>(myFollowedList, HttpStatus.OK);
  }

  /**
   * get users that I am following.
   *
   * @return
   */
  @GetMapping("/my/followed")
  private ResponseEntity<List<UserDTO>> myFollowedUsers() {
    Long userId = jwtService.getUserId();
    List<UserDTO> myFollowedList = followServiceImpl.getAllFollowed(userId);

    return new ResponseEntity<>(myFollowedList, HttpStatus.OK);
  }

  /**
   * Want to know a set of users who are followed by the user of {username}
   *
   * @param username
   * @return
   */
  @GetMapping("/{username}/follower")
  private ResponseEntity<List<UserDTO>> getFollowersOfUser(@PathVariable String username) {
    List<UserDTO> followerList = followServiceImpl.getAllFollower(username);
    return new ResponseEntity<>(followerList, HttpStatus.OK);
  }

  /**
   * Want to know currently who follows the user of {username}.
   *
   * @param username
   * @return
   */
  @GetMapping("/{username}/followed")
  private ResponseEntity<List<UserDTO>> getFollowedOfUser(@PathVariable String username) {
    List<UserDTO> followerList = followServiceImpl.getAllFollowed(username);
    return new ResponseEntity<>(followerList, HttpStatus.OK);
  }

  /**
   * Request to follow some interesting user.
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

  /**
   * Cancel the following.
   *
   * @param userDTO : the target user who will be unfollowed.
   * @return
   */
  @DeleteMapping("/unfollow")
  private ResponseEntity<String> unfollowUser(@RequestBody @Valid UserDTO userDTO) {
    Long userId = jwtService.getUserId();
    followServiceImpl.unfollow(userId, userDTO);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
