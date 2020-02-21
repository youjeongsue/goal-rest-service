package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class EmailAlreadyUsedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public EmailAlreadyUsedException() {
    errorCode = ErrorCode.EMAIL_DUPLICATION;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
