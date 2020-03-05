package com.goal.restservice.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class ResponseVO<T> {

  private @Id @GeneratedValue Long id;

  private String message;
  private boolean check = true;
  private T response;
}
