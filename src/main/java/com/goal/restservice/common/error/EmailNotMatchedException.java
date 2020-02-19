package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class EmailNotMatchedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public EmailNotMatchedException() {
    errorCode = ErrorCode.LOGIN_EMAIL_NOT_MATCHED;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
