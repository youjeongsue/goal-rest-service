package com.goal.restservice.dto;

import com.goal.restservice.domain.Goal;
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

  private Long id;

  private Long goalId;

  @NotBlank
  @Size(min = 1, max = 50)
  private java.lang.String title;

  @Size(min = 0, max = 100)
  private java.lang.String desc;

  private java.lang.String username;

  private java.lang.String category;

  private LocalDate dueDate;

//  @Builder
//  public GoalDto(Long goalId, String title, String desc, Long userId, String category, LocalDate dueDate) {
  public GoalDto(Goal goal, java.lang.String username) {
    this.goalId = goal.getId();
    this.title = goal.getTitle();
    this.desc = goal.getDesc();
    this.username = username;
    this.category = goal.getCategory().getName();
    this.dueDate = goal.getDueDate();
  }

  @Builder
  public GoalDto(Goal goal) {
    this.goalId = goal.getId();
    this.title = goal.getTitle();
    this.desc = goal.getDesc();
    this.category = goal.getCategory().getName();
    this.dueDate = goal.getDueDate();
  }
}
