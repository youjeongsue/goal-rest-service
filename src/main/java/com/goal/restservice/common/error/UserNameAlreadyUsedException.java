package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class UserNameAlreadyUsedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public UserNameAlreadyUsedException() {
    this.errorCode = ErrorCode.USER_DUPLICATION;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
