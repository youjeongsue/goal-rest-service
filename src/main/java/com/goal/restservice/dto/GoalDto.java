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

  private Long goalId;

  @NotBlank
  @Size(min = 1, max = 50)
  private String title;

  @Size(min = 0, max = 100)
  private String desc;

  private String username;

  private String category;

  private LocalDate dueDate;

  @Builder
  public GoalDto(Goal goal) {
    this.goalId = goal.getId();
    this.title = goal.getTitle();
    this.desc = goal.getDesc();
    this.category = goal.getCategory().getName();
    this.dueDate = goal.getDueDate();
  }
}
