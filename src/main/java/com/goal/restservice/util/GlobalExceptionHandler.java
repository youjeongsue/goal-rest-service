package com.goal.restservice.util;

import com.goal.restservice.common.error.EmailAlreadyUsedException;
import com.goal.restservice.common.error.PasswordNotMatchedException;
import com.goal.restservice.common.error.UserNameAlreadyUsedException;
import com.goal.restservice.common.error.EmailNotMatchedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(EmailAlreadyUsedException.class)
  protected ResponseEntity<ErrorResponse> handleEmailAlreadyUsedException(
      EmailAlreadyUsedException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(UserNameAlreadyUsedException.class)
  protected ResponseEntity<ErrorResponse> handleUserNameAlreadyUsedException(
          UserNameAlreadyUsedException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
            new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(PasswordNotMatchedException.class)
  protected ResponseEntity<ErrorResponse> handlePasswordNotMatchedException(
          PasswordNotMatchedException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
            new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(EmailNotMatchedException.class)
  protected ResponseEntity<ErrorResponse> handleUserNameNotMatchedException(
          EmailNotMatchedException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
            new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }
}
