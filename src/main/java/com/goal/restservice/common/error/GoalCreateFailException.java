package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class GoalCreateFailException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public GoalCreateFailException() {
    errorCode = ErrorCode.GOAL_CREATE_FAIL;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}