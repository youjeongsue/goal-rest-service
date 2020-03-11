package com.goal.restservice.util;

import com.goal.restservice.common.error.*;
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

  @ExceptionHandler(UsernameAlreadyUsedException.class)
  protected ResponseEntity<ErrorResponse> handleUsernameAlreadyUsedException(
      UsernameAlreadyUsedException e) {
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

  @ExceptionHandler(GoalDoesNotExistException.class)
  protected ResponseEntity<ErrorResponse> handleGoalDoesNotExistException(
      GoalDoesNotExistException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(GoalCreateFailException.class)
  protected ResponseEntity<ErrorResponse> handleGoalCreateFailException(
      GoalCreateFailException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(SubgoalCreateFailException.class)
  protected ResponseEntity<ErrorResponse> handleSubgoalCreateFailException(
      SubgoalCreateFailException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(NoteCreateFailException.class)
  protected ResponseEntity<ErrorResponse> handleNoteCreateFailException(
          NoteCreateFailException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
            new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(EmailNotMatchedException.class)
  protected ResponseEntity<ErrorResponse> handleUsernameNotMatchedException(
      EmailNotMatchedException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(SubgoalDoesNotExistException.class)
  protected ResponseEntity<ErrorResponse> handleSubgoalDoesNotExistException(
      SubgoalDoesNotExistException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(CategoryDoesNotExistException.class)
  protected ResponseEntity<ErrorResponse> handleCategoryDoesNotExistException(
      CategoryDoesNotExistException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
        new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(NoteDoesNotExistException.class)
  protected ResponseEntity<ErrorResponse> handleNoteDoesNotExistException(
          NoteDoesNotExistException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
            new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(TokenDoesNotExistException.class)
  protected ResponseEntity<ErrorResponse> handleTokenDoesNotExistException(
          TokenDoesNotExistException e) {
    log.info(e.getErrorCode().getMessage());

    return new ResponseEntity<>(
            new ErrorResponse((e.getErrorCode())), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }
}
