package com.goal.restservice.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GoalDto {

  @NotBlank
  @Size(min = 1, max = 50)
  private String title;

  @Size(min = 0, max = 100)
  private String desc;

  private Long userId;

  private String category;

  private LocalDate dueDate;

  @Builder
  public GoalDto(String title, String desc, Long userId, String category, LocalDate dueDate) {
    this.title = title;
    this.desc = desc;
    this.userId = userId;
    this.category = category;
    this.dueDate = dueDate;
  }
}
