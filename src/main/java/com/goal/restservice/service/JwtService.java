package com.goal.restservice.service;

import com.goal.restservice.domain.User;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;

public interface JwtService {
  String createToken(User user);

  boolean isUsable(String jwt);

  String getToken();

  long getUserId();

  String getUserEmail();

  Date getExpireDate();

  Claims get();
}
