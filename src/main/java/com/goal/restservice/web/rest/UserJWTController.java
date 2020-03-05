package com.goal.restservice.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goal.restservice.domain.User;
import com.goal.restservice.security.JwtInterceptor;
import com.goal.restservice.service.JwtService;
import com.goal.restservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class UserJWTController {

  @Autowired StringRedisTemplate redisTemplate;
  private JwtService jwtService;
  private UserServiceImpl userServiceImpl;

  public UserJWTController(JwtService jwtService, UserServiceImpl userServiceImpl) {
    this.jwtService = jwtService;
    this.userServiceImpl = userServiceImpl;
  }

  /**
   * {@code POST /api/auth/login} : Authenticate a new user.
   *
   * @param user with email and password
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with JWT token, or with
   *     status {@code 401 (UNAUTHORIZED)} if user information are invalid.
   */
  @PostMapping("/login")
  public ResponseEntity<JWTToken> login(@RequestBody User user) {

    User loginUser = userServiceImpl.signIn(user.getEmail(), user.getPassword());
    String token = jwtService.createToken(loginUser);

    return ResponseEntity.ok().body(new JWTToken(token));
  }

  /**
   * {@code GET  /api/auth/logout  : Logout the user
   *
   * 1. Get the token from the request header
   * 2. Invalidate the token
   *   - cache the token into redis server with EX(millis) = [token's expire date] - [current date]
   * 3. Following requests with the invalidated token are rejected from {@link JwtInterceptor#preHandle}
   *
   * @return 200
   */
  @GetMapping("/logout")
  public ResponseEntity<String> logout() {

    String token = jwtService.getToken();
    ValueOperations<String, String> vop = redisTemplate.opsForValue();

    vop.set(
        token,
        "expired",
        jwtService.getExpireDate().getTime() - System.currentTimeMillis(),
        TimeUnit.MILLISECONDS);

    return ResponseEntity.ok("bye");
  }

  /** Object to return as body in JWT Authentication. */
  static class JWTToken {

    private String idToken;

    JWTToken(String idToken) {
      this.idToken = idToken;
    }

    @JsonProperty("id_token")
    String getIdToken() {
      return idToken;
    }

    void setIdToken(String idToken) {
      this.idToken = idToken;
    }
  }
}
