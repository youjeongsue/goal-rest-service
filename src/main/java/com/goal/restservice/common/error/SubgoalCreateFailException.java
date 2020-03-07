package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class SubgoalCreateFailException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public SubgoalCreateFailException() {
    errorCode = ErrorCode.SUBGOAL_CREATE_FAIL;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}