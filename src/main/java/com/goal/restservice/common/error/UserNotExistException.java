package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class UserNotExistException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public UserNotExistException() {
    this.errorCode = ErrorCode.USER_DOES_NOT_EXIST;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
