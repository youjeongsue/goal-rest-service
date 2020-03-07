package com.goal.restservice.common.errorcode;

import lombok.Getter;

@Getter
public enum ErrorCode {

  // Member
  EMAIL_DUPLICATION(400, "M001", "Email is already used"),
  USER_DUPLICATION(400, "M002", "Username is already used"),
  LOGIN_PASSWORD_NOT_MATCHED(400, "M003", "Password is not matched"),
  LOGIN_EMAIL_NOT_MATCHED(400, "M004", "email is not registered"),
  USER_DOES_NOT_EXIST(400, "M005", "the user does not exist"),
  TOKEN_DOES_NOT_EXIST(400, "M006", "token does not exist"),
  // Goal
  GOAL_DOES_NOT_EXIST(400, "M007", "goal does not exist"),
  GOAL_CREATE_FAIL(400, "M008", "Creating goal failed"),
  SUBGOAL_CREATE_FAIL(400, "M009", "Creating subgoal failed"),
  SUBGOAL_DOES_NOT_EXIST(400, "M010", "subgoal does not exist"),
  // Category
  CATEGORY_DOES_NOT_EXIST(400, "M011", "category does not exist"),
  ;

  private final String code;
  private final String message;
  private int status;

  ErrorCode(final int status, final String code, final String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }
}
