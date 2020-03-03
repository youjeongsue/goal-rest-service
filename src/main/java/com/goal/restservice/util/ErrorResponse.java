package com.goal.restservice.util;

import com.goal.restservice.common.errorcode.ErrorCode;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  private String message;
  private String code;
  private int status;

  public ErrorResponse(ErrorCode errorCode) {
    this.message = errorCode.getMessage();
    this.code = errorCode.getCode();
    this.status = errorCode.getStatus();
  }
}
