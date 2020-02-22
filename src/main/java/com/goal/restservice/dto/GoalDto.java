package com.goal.restservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// TODO: Add User to goalDto
public class GoalDto {

  private Long id;

  @NotBlank
  @Size(min = 1, max = 50)
  private String title;

  @Size(min = 0, max = 100)
  private String desc;

  private Long userId;

  private String category;
}
