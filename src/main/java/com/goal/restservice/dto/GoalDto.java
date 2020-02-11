package com.goal.restservice.dto;

import com.goal.restservice.domain.goals.Goal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalDto {

  @NotBlank
  @Size(min = 1, max = 50)
  private String title;

  @Size(min = 0, max = 100)
  private String desc;

  private Long userId;

  private Long categoryId;

  public Goal toEntity() {
    return Goal.builder().title(title).desc(desc).build();
  }
}
