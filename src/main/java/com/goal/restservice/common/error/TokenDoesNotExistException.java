package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class TokenDoesNotExistException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public TokenDoesNotExistException () {
    errorCode = ErrorCode.TOKEN_DOES_NOT_EXIST;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}