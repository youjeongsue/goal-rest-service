package com.goal.restservice.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Goal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String category;
  String desc;
}
