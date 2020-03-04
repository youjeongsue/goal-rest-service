package com.goal.restservice.web.rest;

import com.goal.restservice.common.error.EmailAlreadyUsedException;
import com.goal.restservice.common.error.UserNameAlreadyUsedException;
import com.goal.restservice.common.errorcode.ErrorCode;
import com.goal.restservice.domain.User;
import com.goal.restservice.service.JwtServiceImpl;
import com.goal.restservice.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.goal.restservice.dto.UserDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserServiceImpl userServiceImpl;
  private final JwtServiceImpl jwtServiceImpl;

  public UserController(UserServiceImpl userServiceImpl, JwtServiceImpl jwtServiceImpl) {
    this.userServiceImpl = userServiceImpl;
    this.jwtServiceImpl = jwtServiceImpl;
  }

  /**
   * {@code POST api/users } : Create a new user.
   *
   * <p>Create a new user if the login and email are not already used.
   *
   * @param userDTO the use to create
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
   *     user, or with status {@code 400 (Bad Request)} if email is already in use.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   */
  @PostMapping
  private ResponseEntity<String> createUser(@RequestBody @Valid UserDTO userDTO) {

    if (userServiceImpl.isEmailAlreadyUsed(userDTO.getEmail())) {
      throw new EmailAlreadyUsedException();

    } else if (userServiceImpl.isUserNameAlreadyUsed(userDTO.getUserName())) {
      throw new UserNameAlreadyUsedException();
    }

    UserDTO newUser = userServiceImpl.createUser(userDTO);
    // TODO: return email address ?
    return new ResponseEntity<>("success", HttpStatus.CREATED);
  }

  /**
   * {@code GET api/users/profile/me } : Get a user information.
   *
   * <p>Retrieve the user information
   *
   * @return
   */
  @GetMapping("/profile/me")
  private ResponseEntity<UserDTO> readMyProfile() {
    UserDTO user = userServiceImpl.getUserById(jwtServiceImpl.getUserId());
    if (user == null) return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);

    return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
  }

  /**
   * {@code GET api/users/profile?username={username} } : Get a user information.
   *
   * <p>Retrieve the user information
   *
   * @param
   * @return
   */
  @GetMapping("/profile")
  private ResponseEntity<UserDTO> readUserProfile(@RequestParam("username") String userName) {
    UserDTO user = userServiceImpl.getUserByUserName(userName);


    if (user == null) return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);

    return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
  }

  //    /**
  //     * Update user information
  //     *
  //     *
  //     * @param email
  //     * @param user
  //     * @return
  //     */
  //    @PutMapping("/{email:.+}")
  //    private ResponseEntity<User> updateUserByEmail(@PathVariable String email, @RequestBody User
  // user){
  //        if(!jwtServiceImpl.getUserEmail().equals(email))
  //            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "계정 권한 x ");
  //
  //
  //        userServiceImpl.updateUser(user);
  //
  //        User updatedUser = userServiceImpl.getUserByEmail(user.getEmail());
  //        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
  //    }
  //
  //    /**
  //     *  delete a user.
  //     *
  //     * @param email
  //     * @param user
  //     * @return
  //     */
  //    @DeleteMapping("/{email:.+}")
  //    private ResponseEntity<User> deleteUserByEmail(@PathVariable String email, @RequestBody User
  // user){
  //        //TODO :
  //
  //        return new ResponseEntity<User>(user, HttpStatus.OK);
  //    }

}
