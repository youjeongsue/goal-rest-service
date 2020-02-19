package com.goal.restservice.common.error;

public class UnauthorizedException extends RuntimeException {
  private static final long serialVersionUID = -2238030302650813813L;

  public UnauthorizedException() {
    super("계정 권한이 유효하지 않습니다.");
  }
}
