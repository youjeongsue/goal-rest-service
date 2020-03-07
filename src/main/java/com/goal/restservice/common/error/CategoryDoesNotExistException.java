package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class CategoryDoesNotExistException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  ErrorCode errorCode;

  public CategoryDoesNotExistException() {
    errorCode = ErrorCode.CATEGORY_DOES_NOT_EXIST;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
