package com.goal.restservice.common.errorcode;

import lombok.Getter;


@Getter
public enum ErrorCode {

  // Member
  EMAIL_DUPLICATION(400, "M001", "Email is already used"),
  USER_DUPLICATION(400, "M002", "Username is already used"),
  LOGIN_PASSWORD_NOT_MATCHED(400, "M003", "Password is not matched"),
  LOGIN_EMAIL_NOT_MATCHED(400, "M004", "email is not registered"),
  GOAL_DOES_NOT_EXIST(400, "M005", "goal does not exist"),
  GOAL_CREATE_FAIL(400, "M006", "Creating goal failed"),
  SUBGOAL_CREATE_FAIL(400, "M007", "Creating subgoal failed"),
  SUBGOAL_DOES_NOT_EXIST(400, "M008", "subgoal does not exist"),
  CATEGORY_DOES_NOT_EXIST(400, "M009", "category does not exist"),
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
