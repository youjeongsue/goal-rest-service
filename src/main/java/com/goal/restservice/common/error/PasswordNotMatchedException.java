package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class PasswordNotMatchedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public PasswordNotMatchedException() {
    errorCode = ErrorCode.LOGIN_PASSWORD_NOT_MATCHED;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
